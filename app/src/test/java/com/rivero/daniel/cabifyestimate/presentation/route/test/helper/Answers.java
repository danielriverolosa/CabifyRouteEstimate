package com.rivero.daniel.cabifyestimate.presentation.route.test.helper;

import com.rivero.daniel.cabifyestimate.domain.interactor.InteractorCallback;
import com.rivero.daniel.cabifyestimate.infrastructure.Supplier;

import org.mockito.stubbing.Answer;

public class Answers {

    public static <T, R> Answer<Void> callBackAnswer(Supplier<R> supplier) {
        return invocation -> {
            InteractorCallback<T> callback = null;
            for (Object arg:invocation.getArguments()) {
                if (arg instanceof InteractorCallback) {
                    callback = (InteractorCallback<T>) arg;
                    break;
                }
            }
            if (callback == null) {
                throw new IllegalArgumentException("missing callback argument");
            }
            R result = supplier.get();
            if (result instanceof Throwable) {
                callback.onError((Throwable) result);
            } else callback.onSuccess((T) result);
            return null;
        };
    }

}
