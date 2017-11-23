package com.rivero.daniel.cabifyestimate.presentation.base;


public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T view;

    @Override
    public void onCreate(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
