package com.epam.ag.dao;

import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Govorov Andrey
 */
public class JdbcDaoFactory<E extends BaseEntity> extends DaoFactory {

    public static final String DAO_PACKAGE_PATH = "com.epam.ag.dao";
    private static final Logger log = LoggerFactory.getLogger(JdbcDaoFactory.class);

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
            dao = (T) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Unable to create DAO instance ({})", DaoClassName);
            throw new RuntimeException("Unable to create/find DAO instance", e);
        }

        log.trace("DAO successfully created ({})", DaoClassName);
        return (T) dao;
    }
}