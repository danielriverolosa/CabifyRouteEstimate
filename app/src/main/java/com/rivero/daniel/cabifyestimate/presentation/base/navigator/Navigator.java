package com.rivero.daniel.cabifyestimate.presentation.base.navigator;

import android.content.Context;

import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ActivityContext;

import javax.inject.Inject;

public class Navigator {

    private Context context;

    @Inject
    Navigator(@ActivityContext Context context) {
        this.context = context;
    }

}
