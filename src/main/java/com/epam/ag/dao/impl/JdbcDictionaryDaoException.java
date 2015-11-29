package com.epam.ag.dao.impl;

import java.sql.SQLException;

public class JdbcDictionaryDaoException extends RuntimeException {
    public JdbcDictionaryDaoException(String s, SQLException e) {
        super(s, e);
    }
}
