package com.epam.ag.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelper {

    private static final Logger log = LoggerFactory.getLogger(JdbcHelper.class);

    public static Long getReturningID(Statement statement) {
        Long id = null;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int idColumnIndex = generatedKeys.findColumn("id");
                id = generatedKeys.getLong( idColumnIndex );
            }
            else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        } catch (SQLException e) {
            log.error("Returning failed");
            e.printStackTrace();
        }
        return id;
    }

    public static java.sql.Date convertToSQLDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
