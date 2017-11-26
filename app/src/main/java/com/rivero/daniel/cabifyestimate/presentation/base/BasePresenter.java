package com.rivero.daniel.cabifyestimate.presentation.base;


import com.rivero.daniel.cabifyestimate.presentation.base.navigator.Navigator;

import javax.inject.Inject;

public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    @Inject
    Navigator navigator;

    private T view;

    @Override
    public void onCreate(T view) {
        this.view = view;
    }

    @Override
    public void onResume() {}

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
