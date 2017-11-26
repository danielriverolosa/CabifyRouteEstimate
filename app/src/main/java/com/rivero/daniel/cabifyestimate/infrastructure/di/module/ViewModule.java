package com.rivero.daniel.cabifyestimate.infrastructure.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.rivero.daniel.cabifyestimate.data.service.LocationServiceImpl;
import com.rivero.daniel.cabifyestimate.data.service.PermissionServiceImpl;
import com.rivero.daniel.cabifyestimate.domain.service.LocationService;
import com.rivero.daniel.cabifyestimate.domain.service.PermissionService;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ActivityContext;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.navigator.Navigator;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.RouteSelectorPresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.RouteSelectorPresenterImpl;

import javax.inject.Singleton;

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
    @ActivityContext
    Context provideContext() {
        return activity != null ? activity : fragment.getContext();
    }

    @Provides
    @ViewScope
    Navigator navigator() {
        return new Navigator(provideContext());
    }

    @Provides
    @ViewScope
    LocationService provideLocationService(LocationServiceImpl locationService) {
        return locationService;
    }

    @Provides
    @ViewScope
    PermissionService providePermissionService(PermissionServiceImpl permissionService) {
        return permissionService;
    }

}
