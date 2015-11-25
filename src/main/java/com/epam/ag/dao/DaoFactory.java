package com.epam.ag.dao;

import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Govorov Andrey
 */
public class DaoFactory<T extends BaseEntity> {

    public static final String DAO_PACKAGE_PATH = "com.epam.ag.dao";
    private static DaoFactory instance;
    private static final Logger log = LoggerFactory.getLogger(DaoFactory.class);

    /**
     * Creating or returning base class
     *
     * @return instance of DAO
     */
    public static DaoFactory newInstance() {
        if (instance == null) {
            log.trace("Creating new JDBC DAO.");
            instance = new JdbcDaoFactory();
        }
        return instance;
    }

    public static GenericDao getDao(Class clazz) {
        //
        // TODO Как лучше указать пакет?
        //
        //String currentPackage = this.getClass().getPackage().getName();
        String DaoClassName = DAO_PACKAGE_PATH + "." + clazz.getSimpleName() + "Dao";

        Class c = null;
        GenericDao dao = null;
        try {
            log.trace("Trying to create DAO instance ({})", DaoClassName);
            c = Class.forName(DaoClassName);
            dao = (GenericDao) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Unable to create DAO instance ({})", DaoClassName);
            throw new RuntimeException("Unable to create/find DAO instance", e);
        }
        log.trace("DAO successfully created ({})", DaoClassName);
        return dao;
    }
}
