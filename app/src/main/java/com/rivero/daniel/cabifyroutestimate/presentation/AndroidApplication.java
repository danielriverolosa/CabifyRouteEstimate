package com.rivero.daniel.cabifyroutestimate.presentation;


import android.app.Application;

import com.rivero.daniel.cabifyroutestimate.BuildConfig;
import com.rivero.daniel.cabifyroutestimate.infrastructure.di.component.ApplicationComponent;
import com.rivero.daniel.cabifyroutestimate.infrastructure.di.component.DaggerApplicationComponent;
import com.rivero.daniel.cabifyroutestimate.infrastructure.di.module.ApplicationModule;

import timber.log.Timber;

public class AndroidApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initTimber();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
