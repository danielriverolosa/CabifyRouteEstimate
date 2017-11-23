package com.rivero.daniel.cabifyestimate.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rivero.daniel.cabifyestimate.infrastructure.di.component.DaggerViewComponent;
import com.rivero.daniel.cabifyestimate.infrastructure.di.component.ViewComponent;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.presentation.AndroidApplication;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    protected ViewComponent buildInjector(ViewModule viewModule) {
        return DaggerViewComponent.builder()
                .viewModule(viewModule)
                .applicationComponent(((AndroidApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
    }

    public abstract void initializeInjector();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutContainer(), container, false);
    }

    @LayoutRes
    public abstract int getLayoutContainer();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void showMessage(String message) {
        BaseActivity baseActivity = ((BaseActivity) getActivity());
        if (baseActivity != null) {
            baseActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(int message) {
        BaseActivity baseActivity = ((BaseActivity) getActivity());
        if (baseActivity != null) {
            baseActivity.showMessage(message);
        }
    }

    @Override
    public void close() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }


}
