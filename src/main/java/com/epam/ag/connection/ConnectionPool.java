package com.epam.ag.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ConnectionPool {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static Connection instance;
    private List<DbConnection> connectionList;

    private ConnectionPool() {
        connectionList = new ArrayList<>();
    }

    public static Connection getConnection() {
        if (instance == null) {
            // create new connection
            instance = new DbConnection();
        }
        return instance;
    }
}