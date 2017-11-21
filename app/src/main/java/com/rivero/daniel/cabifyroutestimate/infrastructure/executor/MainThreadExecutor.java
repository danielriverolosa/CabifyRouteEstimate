package com.rivero.daniel.cabifyroutestimate.infrastructure.executor;

public interface MainThreadExecutor {

    void post(final Runnable runnable);
}
