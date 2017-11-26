package com.rivero.daniel.cabifyestimate.presentation.base.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ActivityContext;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.route.fragment.EstimateListFragment;

import javax.inject.Inject;

@ViewScope
public class Navigator {

    private Context context;

    @Inject
    public Navigator(@ActivityContext Context context) {
        this.context = context;
    }

    protected void showFragment(int containerId, Fragment fragment, Boolean backEnable) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);

        if (backEnable) {
            String fragmentTag = retrieveFragmentTag(fragment);
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }

    protected void addFragment(int containerId, Fragment fragment, Boolean backEnable) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);

        if (backEnable) {
            String fragmentTag = retrieveFragmentTag(fragment);
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }

    private FragmentManager getFragmentManager() {
        return ((FragmentActivity) context).getSupportFragmentManager();
    }

    private String retrieveFragmentTag(Fragment fragment) {
        if (fragment == null) {
            return null;
        }
        return fragment.getClass().getSimpleName();
    }

    public void showEstimateList(Route route) {
        addFragment(R.id.fragment_container, EstimateListFragment.getInstance(route), true);
    }

}
