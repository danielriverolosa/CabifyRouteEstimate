package com.rivero.daniel.cabifyestimate.infrastructure.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.navigator.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    private Activity activity;
    private Fragment fragment;

    public ViewModule(Activity activity) {
        this.activity = activity;
    }

    public ViewModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @ViewScope
    Context context() {
        return activity != null ? activity : fragment.getContext();
    }

    @Provides
    @ViewScope
    Activity activity() {
        return activity;
    }

    @Provides
    @ViewScope
    Fragment fragment() {
        return fragment;
    }

    @Provides
    @ViewScope
    Navigator navigator() {
        return new Navigator(context());
    }
}
