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
    private final Connection connection;

    /**
     * Create connection for whole factory
     */
    public JdbcDaoFactory() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnect();
        log.trace("Connection pool: {}", connectionPool);
        log.trace("Connect: {}", connection);
        log.trace("JdbcDao factory ready.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        //
        // TODO пакет можно положить в пропертя и читать оттуда
        //
        String daoClassName = DAO_PACKAGE_PATH + ".Jdbc" + clazz.getSimpleName();
        GenericDao dao = null;
        try {
            log.trace("Trying to create DAO instance ({})", daoClassName);
            Class c = Class.forName(daoClassName);

            // Looking for constructor with 'Connection' class as parameter
            dao = (T) c.getDeclaredConstructor(Connection.class).newInstance(connection);
            log.trace("Created DAO: {}", dao);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.error("Unable to create DAO instance {}", daoClassName, e);
            throw new RuntimeException("Unable to create/find DAO instance", e);
        }

        log.trace("DAO successfully created ({})", daoClassName);
        return (T) dao;
    }

    @Override
    public void beginTransaction() {
        log.trace("Begin TX method");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        log.trace("Commit method");
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        log.trace("Rollback method");
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        log.trace("Close method");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}