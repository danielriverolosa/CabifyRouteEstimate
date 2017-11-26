package com.rivero.daniel.cabifyestimate.data.repository.datasource.api;


public interface ApiClientGenerator {
    <T> T generateApi(Class<T> service);
}
