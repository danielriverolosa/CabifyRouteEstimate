package com.rivero.daniel.cabifyestimate.presentation.base;


import android.support.annotation.StringRes;

public interface BaseView {

    void showMessage(String message);

    void showMessage(@StringRes int message);

    void close();

}
