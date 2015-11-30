package com.epam.ag.dao.impl.exception;

import java.sql.SQLException;

/**
 * @author Govorov Andrey
 */
public class JdbcUserRoleException extends RuntimeException {
    public JdbcUserRoleException(String message, Throwable t) {
        super(message, t);
    }
}
