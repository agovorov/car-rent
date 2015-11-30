package com.epam.ag.dao.impl;

import com.epam.ag.dao.UserRoleDao;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcUserRoleDao implements UserRoleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcUserRoleDao.class);
    private static PropertiesManager pm = PropertiesManager.getInstance();
    private Connection connection;

    public JdbcUserRoleDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public UserRole save(UserRole entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private UserRole insert(UserRole entity) {
        log.trace("insert statement: {}", entity);
        String query = pm.get("userole.insert");
        return JdbcCommonDictDao.insert(query, connection, entity, UserRole.class);
    }

    private UserRole update(UserRole entity) {
        log.trace("update statement: {}", entity);
        String query = pm.get("userole.update");
        return JdbcCommonDictDao.update(query, connection, entity, UserRole.class);
    }

    @Override
    public UserRole getByParameter(String param, String value) {
        return null;
    }

    @Override
    public UserRole getById(Long id) {
        log.trace("getById SQL statement: id={}", id);
        String query = pm.get("userole.getById");
        return JdbcCommonDictDao.getById(query, connection, id, UserRole.class);
    }

    @Override
    public List<UserRole> getAll() {
        log.trace("getAll SQL statement");
        String query = pm.get("userole.getAll");
        return JdbcCommonDictDao.getAll(query, connection, UserRole.class);
    }

    @Override
    public boolean delete(UserRole entity) {
        String query = pm.get("userole.delete");
        log.trace("SQL delete statement: {}", entity);
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
