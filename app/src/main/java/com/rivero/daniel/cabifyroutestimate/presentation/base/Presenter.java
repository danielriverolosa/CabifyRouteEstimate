package com.rivero.daniel.cabifyroutestimate.presentation.base;


public interface Presenter<T extends BaseView> {

    void onCreate(T view);

    void onResume();

}
