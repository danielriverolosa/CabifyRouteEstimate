package com.rivero.daniel.cabifyestimate.presentation.base;


public interface Presenter<T extends BaseView> {

    void onCreate(T view);

    void onResume();

}
