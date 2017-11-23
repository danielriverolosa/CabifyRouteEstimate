package com.rivero.daniel.cabifyestimate.domain.interactor;


public interface InteractorCallback<T> {

    void onSuccess(T data);

    void onError(Throwable t);

}
