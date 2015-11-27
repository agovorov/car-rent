package com.epam.ag.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ConnectionPool {

    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/car-rent";
    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static Connection instance;
    //private List<DbConnection> connectionList;

    private ConnectionPool() {
        //connectionList = new ArrayList<>();
    }

    public static Connection getConnection() {
        if (instance == null) {
            // create new connection
            //instance = new DbConnection();
            try {
                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection(CONNECTION_STRING, "postgres", "postgres");
            } catch (SQLException | ClassNotFoundException e) {
                log.error("Unable to create new connection.", e);
                throw new RuntimeException("Unable to create new connection. Try later...", e);
            }
        }
        return instance;
    }

    /**
     * Пока просто закрываем, дальше будем возвращать в пул
     */
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Unable to close connection", e);
            throw new RuntimeException("Unable to close connection", e);
        }
    }
}