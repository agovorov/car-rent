package com.epam.ag.dao;

import com.epam.ag.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcDaoFactory extends DaoFactory implements GenericDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcDaoFactory.class);

    // В фабрике этого не должно
    // Не там)
    @Deprecated
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

    @Override
    public BaseEntity save(BaseEntity entity) {
        return null;
    }

    @Override
    public BaseEntity getByParameter(String param, String value) {
        return null;
    }

    @Override
    public BaseEntity getById(Long id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean delete(BaseEntity entity) {
        return false;
    }
}
