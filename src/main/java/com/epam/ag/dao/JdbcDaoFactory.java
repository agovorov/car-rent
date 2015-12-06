package com.epam.ag.dao;

import com.epam.ag.connection.ConnectionPool;
import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Govorov Andrey
 */
public class JdbcDaoFactory<E extends BaseEntity> extends DaoFactory {

    public static final String DAO_PACKAGE_PATH = "com.epam.ag.dao.impl";
    private static final Logger log = LoggerFactory.getLogger(JdbcDaoFactory.class);
    Connection connection;

    // Here
//    private final ConnectionPool connectionPool = ConnectionPool.getInstance(
//            propertyManager.getProperty("driverName"),
//            propertyManager.getProperty("url"),
//            propertyManager.getProperty("login"),
//            propertyManager.getProperty("password"),
//            Integer.parseInt(propertyManager.getProperty("maxConnections")));

    // Add ConnectionPoll here
    // and call

    public JdbcDaoFactory() {
        log.trace("Get free connection from Connection pool.");
        ConnectionPool instance = ConnectionPool.getInstance();
        connection = instance.getConnection();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        //
        // TODO пакет можно положить в пропертя и читать оттуда
        //
        String DaoClassName = DAO_PACKAGE_PATH + ".Jdbc" + clazz.getSimpleName();
        GenericDao dao = null;
        try {
            log.trace("Trying to create DAO instance ({})", DaoClassName);
            Class c = Class.forName(DaoClassName);

            // Looking for constructor with 'Connection' class as parameter
            dao = (T) c.getDeclaredConstructor(Connection.class).newInstance(connection);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.error("Unable to create DAO instance ({}, {})", DaoClassName, e);
            throw new RuntimeException("Unable to create/find DAO instance", e);
        }

        log.trace("DAO successfully created ({})", DaoClassName);
        return (T) dao;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        log.trace("close method");
    }
}