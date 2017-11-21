package com.rivero.daniel.cabifyroutestimate.presentation.base.navigator;

import android.content.Context;

import com.rivero.daniel.cabifyroutestimate.infrastructure.di.scope.ViewScope;

import javax.inject.Inject;

@ViewScope
public class Navigator {

    private Context context;

    @Inject
    public Navigator(Context context) {
        this.context = context;
    }
}
