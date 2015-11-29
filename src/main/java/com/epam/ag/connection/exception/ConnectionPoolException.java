package com.epam.ag.connection.exception;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception cause) {
        super(message, cause);
    }
}
