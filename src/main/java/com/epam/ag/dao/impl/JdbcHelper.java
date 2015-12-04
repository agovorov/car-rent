package com.epam.ag.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelper {
    public static Long getReturningID(Statement statement) {
        Long id = null;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static java.sql.Date convertToSQLDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
