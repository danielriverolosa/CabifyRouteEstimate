package com.rivero.daniel.cabifyestimate.presentation.route;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseActivity;
import com.rivero.daniel.cabifyestimate.presentation.route.fragment.RouteSelectorFragment;


public class RouteCalculatorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_route_calculator;
    }

    @Override
    public void initializeInjector() {
        buildInjector(new ViewModule(this)).inject(this);
    }
}
