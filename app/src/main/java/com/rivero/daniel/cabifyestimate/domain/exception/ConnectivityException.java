package com.rivero.daniel.cabifyestimate.domain.exception;


public class ConnectivityException extends RepositoryException {
    public ConnectivityException() {
        super();
    }
    public ConnectivityException(String message) {
        super(message);
    }
}
