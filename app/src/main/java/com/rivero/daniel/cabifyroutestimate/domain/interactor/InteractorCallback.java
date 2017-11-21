package com.rivero.daniel.cabifyroutestimate.domain.interactor;


public interface InteractorCallback<T> {

    void onSuccess(T data);

    void onError(Throwable t);

}
