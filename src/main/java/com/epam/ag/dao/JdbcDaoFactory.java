package com.epam.ag.dao;

import com.epam.ag.connection.ConnectionPool;
import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public JdbcDaoFactory() {
        log.trace("Get free connection from Connection pool.");
        ConnectionPool instance = ConnectionPool.getInstance();
        log.trace("Got connection pool instance.");
        connection = instance.getConnection();
        log.trace("Got connection");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        //
        // TODO пакет можно положить в пропертя и читать оттуда
        //
        String DaoClassName = DAO_PACKAGE_PATH + ".Jdbc" + clazz.getSimpleName();

        Class c = null;
        GenericDao dao = null;
        try {
            log.trace("Trying to create DAO instance ({})", DaoClassName);
            c = Class.forName(DaoClassName);

            // Looking for constructor with 'Connection' class as parameter
            dao = (T) c.getDeclaredConstructor(Connection.class).newInstance(connection);
            //
            // TODO Вот тебе на... сколько эксепшенов.
            //
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Unable to create DAO instance ({})", DaoClassName);
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