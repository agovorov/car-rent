package com.epam.ag.dao.impl;

import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.dao.impl.exception.JdbcDictionaryDaoException;
import com.epam.ag.dao.impl.exception.JdbcUserDaoException;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcUserDao extends JdbcAbstractDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);

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
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setLong(6, user.getRoleId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            user.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL", e);
            throw new JdbcDaoException("Unable to query SQL", e);
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
            log.error("Unable to query SQL {}, {}", user, e);
            throw new JdbcDaoException("Unable to query SQL", e);
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
        log.trace("User statement: {}", id);
        String query = pm.get("user.getById");

        PreparedStatement ps;
        User user = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPhone(rs.getString("phone"));
                user.setRole(new UserRole(
                        rs.getLong("role_id"),
                        rs.getString("value_ru"),
                        rs.getString("value_en")
                ));
            }
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {} ", user, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        log.trace("getAll");
        String query = pm.get("user.getAll");
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        User user;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPhone(rs.getString("phone"));
                user.setRole(new UserRole(
                        rs.getLong("role_id"),
                        rs.getString("value_ru"),
                        rs.getString("value_en")
                ));
                userList.add(user);
            }
            log.trace("{}",userList);
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return userList;
    }

    @Override
    public boolean delete(User entity) {
        log.trace("JdbcUserDao delete");
        String query = pm.get("user.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
