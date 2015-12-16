package com.epam.ag.dao.impl.exception;

/**
 * @author Govorov Andrey
 */
public class DaoFactoryException extends RuntimeException {
    public DaoFactoryException() {
        super();
    }

    public DaoFactoryException(String message) {
        super(message);
    }

    public DaoFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoFactoryException(Throwable cause) {
        super(cause);
    }
}
