package com.epam.ag.dao.impl;

import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.impl.exception.JdbcUserDaoException;
import com.epam.ag.model.User;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcUserDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
    private Connection connection;
    private PropertiesManager pm = PropertiesManager.getInstance();

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public User save(User entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private User insert(User user) {
        log.trace("User insert statement: {}", user);
        String query = pm.get("user.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setLong(6, user.getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", user);
            throw new JdbcUserDaoException("Unable to query SQL", e);
        }
        return user;
    }

    private User update(User user) {
        log.trace("User update statement: {}", user);
        String query = pm.get("user.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setLong(6, user.getRoleId());
            ps.setLong(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", user);
            throw new JdbcUserDaoException("Unable to query SQL", e);
        }
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
