package com.epam.ag.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO При создании передать locale и вытаскивать ее данные

/**
 * @author Govorov Andrey
 */
public abstract class DaoFactory implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(DaoFactory.class);
    //    private static Locale locale;
    private static DaoFactory instance;

    /**
     * Creating or returning base class
     *
     * @return instance of DAO factory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            log.trace("Creating new JDBC DAO.");
            instance = new JdbcDaoFactory();
        }
        return instance;
    }

    public abstract <T extends GenericDao> T getDao(Class<T> clazz);

    public abstract void close();

    public abstract void beginTransaction();

    public abstract void commit();

    public abstract void rollback();

}
