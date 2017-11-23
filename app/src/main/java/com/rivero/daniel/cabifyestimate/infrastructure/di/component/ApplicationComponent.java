package com.rivero.daniel.cabifyestimate.infrastructure.di.component;

import android.content.Context;

import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ApplicationModule;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.ThreadExecutor;
import com.rivero.daniel.cabifyestimate.presentation.AndroidApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AndroidApplication application);

    Context context();
    ThreadExecutor threadExecutor();
    MainThreadExecutor mainThreadExecutor();
}
