package com.rivero.daniel.cabifyestimate.data.service;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;

import com.rivero.daniel.cabifyestimate.domain.service.PermissionService;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ActivityContext;

import javax.inject.Inject;

public class PermissionServiceImpl implements PermissionService {

    private Context context;

    @Inject
    PermissionServiceImpl(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void requestPermissions(int requestCode, String... permissions) {
        ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
    }
}
