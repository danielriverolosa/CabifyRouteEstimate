package com.rivero.daniel.cabifyestimate.data.repository;


import com.rivero.daniel.cabifyestimate.domain.exception.ConnectionTimeoutException;
import com.rivero.daniel.cabifyestimate.domain.exception.HttpException;
import com.rivero.daniel.cabifyestimate.domain.exception.NoConnectionException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseRepository {

    protected <T> T executeCall(Call<T> call) {
        try {
            Response<T> response = call.execute();

            if (!response.isSuccessful()) {
                handleHttpError(response);
            }

            return response.body();
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                throw new ConnectionTimeoutException();
            } else {
                throw new NoConnectionException();
            }
        }
    }

    protected <T> void handleHttpError(Response<T> response) throws IOException {
        throw new HttpException(response);
    }

}
