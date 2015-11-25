package com.epam.ag.dao;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.GenericDao;
import com.epam.ag.model.BaseEntity;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcDaoFactory extends DaoFactory implements GenericDao {

    @Override
    public BaseEntity insert(BaseEntity entity) {
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
    public BaseEntity update(BaseEntity entity) {
        return null;
    }

    @Override
    public boolean delete(BaseEntity entity) {
        return false;
    }
}
