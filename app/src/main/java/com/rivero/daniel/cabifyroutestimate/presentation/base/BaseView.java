package com.rivero.daniel.cabifyroutestimate.presentation.base;


import android.support.annotation.StringRes;

public interface BaseView {

    void showMessage(String message);

    void showMessage(@StringRes int message);

    void close();

}
