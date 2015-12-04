package com.epam.ag.dao.impl.exception;

/**
 * @author Govorov Andrey
 */
public class JdbcDaoException extends RuntimeException {

    public JdbcDaoException(String message, Throwable t) {
        super(message, t);
    }

    public JdbcDaoException(String message) {
        super(message);
    }
}
