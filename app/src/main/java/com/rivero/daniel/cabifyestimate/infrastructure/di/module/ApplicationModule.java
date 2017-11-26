package com.rivero.daniel.cabifyestimate.infrastructure.di.module;

import android.content.Context;

import com.rivero.daniel.cabifyestimate.data.service.LocationServiceImpl;
import com.rivero.daniel.cabifyestimate.data.service.PermissionServiceImpl;
import com.rivero.daniel.cabifyestimate.domain.service.LocationService;
import com.rivero.daniel.cabifyestimate.domain.service.PermissionService;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.ApiClientGenerator;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.RetrofitApiClientGenerator;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ApplicationContext;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.JobExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.ThreadExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.UiThread;
import com.rivero.daniel.cabifyestimate.presentation.AndroidApplication;

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
    @ApplicationContext
    public Context providesApplicationContext() {
        return application.getApplicationContext();
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

    @Provides
    @Singleton
    ApiClientGenerator provideApiClientGenerator(RetrofitApiClientGenerator apiClientGenerator) {
        return apiClientGenerator;
    }
}
