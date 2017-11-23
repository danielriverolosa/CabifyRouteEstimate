package com.rivero.daniel.cabifyestimate.presentation.base.navigator;

import android.content.Context;

import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;

import javax.inject.Inject;

@ViewScope
public class Navigator {

    private Context context;

    @Inject
    public Navigator(Context context) {
        this.context = context;
    }
}