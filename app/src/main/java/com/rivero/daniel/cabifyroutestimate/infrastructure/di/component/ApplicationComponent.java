package com.rivero.daniel.cabifyroutestimate.infrastructure.di.component;

import android.content.Context;

import com.rivero.daniel.cabifyroutestimate.infrastructure.di.module.ApplicationModule;
import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyroutestimate.infrastructure.executor.ThreadExecutor;
import com.rivero.daniel.cabifyroutestimate.presentation.AndroidApplication;

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
