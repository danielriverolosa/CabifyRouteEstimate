package com.rivero.daniel.cabifyroutestimate.infrastructure.executor;


import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UiThread implements MainThreadExecutor {
    private Handler handler;

    @Inject
    public UiThread() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
