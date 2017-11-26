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

import java.io.Serializable;

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

    protected abstract void initializeInjector();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutContainer(), container, false);
    }

    @LayoutRes
    protected abstract int getLayoutContainer();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public <T extends Serializable> T getParamByClass(Class<T> paramClass) {
        return getParamByClass(paramClass, "");
    }

    @SuppressWarnings("unchecked")
    protected <T extends Serializable> T getParamByClass(Class<T> paramClass, String tag) {
        if (getArguments() == null) {
            return null;
        }
        return (T) getArguments().getSerializable(paramClass.getName() + tag);
    }

    protected <T extends Serializable> void setParamByClass(Class<T> registeredClass, T object) {
        setParamByClass(registeredClass, object, "");
    }

    protected <T extends Serializable> void setParamByClass(Class<T> registeredClass, T object, String tag) {
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
        getArguments().putSerializable(registeredClass.getName() + tag, object);
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
