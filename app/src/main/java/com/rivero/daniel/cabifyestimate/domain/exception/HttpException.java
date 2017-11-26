package com.rivero.daniel.cabifyestimate.domain.exception;


import retrofit2.Response;

public class HttpException extends RuntimeException {

    private Response response;

    public HttpException(Response response) {
        super("Http error: " + Integer.toString(response.code()));
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
