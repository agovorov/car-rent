package com.epam.ag.service;

/**
 * @author by Govorov Andrey.
 */
public class VehicleServiceException extends RuntimeException {
    public VehicleServiceException() {
    }

    public VehicleServiceException(String message) {
        super(message);
    }

    public VehicleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleServiceException(Throwable cause) {
        super(cause);
    }
}
