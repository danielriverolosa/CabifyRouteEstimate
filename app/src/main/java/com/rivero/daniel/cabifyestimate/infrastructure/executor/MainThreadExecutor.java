package com.rivero.daniel.cabifyestimate.infrastructure.executor;

public interface MainThreadExecutor {

    void post(final Runnable runnable);
}
