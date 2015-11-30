package com.epam.ag.dao.impl.exception;

import java.sql.SQLException;


public class JdbcUserDaoException extends RuntimeException {
    public JdbcUserDaoException(String s, SQLException e) {
    }
}
