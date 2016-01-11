package com.epam.ag.service.exception;

/**
 * @author Govorov Andrey
 */
public class GalleryServiceException extends RuntimeException {
    public GalleryServiceException() {
        super();
    }

    public GalleryServiceException(String message) {
        super(message);
    }

    public GalleryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GalleryServiceException(Throwable cause) {
        super(cause);
    }
}
