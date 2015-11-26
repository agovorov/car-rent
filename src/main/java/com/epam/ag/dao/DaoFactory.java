package com.epam.ag.dao;

import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Govorov Andrey
 */
public abstract class DaoFactory {

    private static DaoFactory instance;
    private static final Logger log = LoggerFactory.getLogger(DaoFactory.class);

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

    // Could be...
    // public abstract VehicleDao createVehicleDao();
    // public abstract UserDao createUserDao();
    // public abstract OrderDao createOrderDao();
}
