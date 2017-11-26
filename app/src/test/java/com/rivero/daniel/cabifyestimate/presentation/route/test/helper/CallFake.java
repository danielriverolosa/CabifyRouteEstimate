package com.rivero.daniel.cabifyestimate.presentation.route.test.helper;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallFake<T> implements Call<T> {

    private Response<T> response;

    public CallFake(Response<T> response) {
        this.response = response;
    }

    public static <T> CallFake<T> buildSuccess(T body) {
        return new CallFake<>(Response.success(body));
    }

    public static <T> CallFake<T> buildHttpError(int errorCode, String contentType, String content) {
        return new CallFake<>(Response.error(errorCode, ResponseBody.create(MediaType.parse(contentType), content)));
    }

    @Override
    public Response<T> execute() throws IOException {
        return response;
    }

    @Override
    public void enqueue(Callback<T> callback) {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}

