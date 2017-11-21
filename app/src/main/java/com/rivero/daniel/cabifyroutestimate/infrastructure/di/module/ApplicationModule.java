package com.rivero.daniel.cabifyroutestimate.infrastructure.di.module;

import android.content.Context;

import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.JobExecutor;
import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.ThreadExecutor;
import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.UiThread;
import com.rivero.daniel.cabifyroutestimate.presentation.AndroidApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    MainThreadExecutor provideMainThreadExecutor(UiThread uiThread) {
        return uiThread;
    }

}
