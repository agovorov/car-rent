package com.epam.ag.dao.impl;

import com.epam.ag.dao.UserDao;
import com.epam.ag.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcUserDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User entity) {
        log.trace("save");
        return null;
    }

    @Override
    public User getByParameter(String param, String value) {
        log.trace("JdbcUserDao getByParameter");
        return null;
    }

    @Override
    public User getById(Long id) {
        log.trace("JdbcUserDao getById");
        return null;
    }

    @Override
    public List<User> getAll() {
        log.trace("JdbcUserDao getAll");
        return null;
    }

    @Override
    public boolean delete(User entity) {
        log.trace("JdbcUserDao delete");
        return false;
    }
}
