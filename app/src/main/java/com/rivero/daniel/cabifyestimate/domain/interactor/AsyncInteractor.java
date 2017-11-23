package com.rivero.daniel.cabifyestimate.domain.interactor;


import com.rivero.daniel.cabifyestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.ThreadExecutor;

import javax.inject.Inject;

import timber.log.Timber;

public abstract class AsyncInteractor<T> {

    @Inject
    ThreadExecutor threadExecutor;
    @Inject
    MainThreadExecutor mainThreadExecutor;

    private InteractorCallback<T> callback;

    public AsyncInteractor(ThreadExecutor threadExecutor, MainThreadExecutor mainThreadExecutor) {
        this.threadExecutor = threadExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public void execute(InteractorCallback<T> callback, Object... params) {
        try {
            this.callback = callback;
            threadExecutor.run(() -> executePipeline(params));
        } catch (Exception e) {
            //Notify exception throw in UI thread
            notifyError(e);
            Timber.e("The execution have an error: ", e);
        }
    }

    protected void executePipeline(Object... params) {
        try {
            runInBackground(params);
        } catch (Exception e) {
            notifyError(e);
            Timber.e("The execution have an error: ", e);
        }
    }

    protected abstract void runInBackground(Object... params);

    protected void notifySuccess(T data) {
        if (callback == null) {
            throw new IllegalStateException("The callback is null!!!");
        }

        mainThreadExecutor.post(() -> callback.onSuccess(data));
        Timber.d("Success: " + data.toString());
    }

    protected void notifyError(Throwable e) {
        if (callback == null) {
            throw new IllegalStateException("The callback is null!!!");
        }

        mainThreadExecutor.post(() -> callback.onError(e));
        Timber.d("Fail: " + e.toString());
    }

}
