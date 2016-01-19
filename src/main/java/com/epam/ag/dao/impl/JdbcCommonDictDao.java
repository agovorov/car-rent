package com.epam.ag.dao.impl;

import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.BaseEntity;
import com.epam.ag.model.dict.DictionaryBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCommonDictDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcCommonDictDao.class);

    public static <T> T update(String query, Connection connection, DictionaryBase entity, Class<T> clazz) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, entity.getValue("ru"));
            ps.setString(2, entity.getValue("en"));
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", entity);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return clazz.cast(entity);
    }

    public static <T> T insert(String query, Connection connection, DictionaryBase entity, Class<T> clazz) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getValue("ru"));
            ps.setString(2, entity.getValue("en"));
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            entity.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", entity);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return clazz.cast(entity);
    }

    public static <T> T getById(String query, Connection connection, Long id, Class<T> clazz) {
        Object entity = null;
        PreparedStatement ps = null;
        String value_ru = null;
        String value_en = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                i++;
                value_ru = rs.getString("value_ru");
                value_en = rs.getString("value_en");
            }

            if (i == 0) return null;
            log.trace("value ru {}, value en {}", value_ru, value_en);

            Class c = Class.forName(clazz.getName());
            entity = c.getDeclaredConstructor(Long.class, String.class, String.class).newInstance(id, value_ru, value_en);
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException e) {
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return clazz.cast(entity);
    }

    public static boolean delete(String query, Connection connection, BaseEntity entity) {
        PreparedStatement ps = null;
        boolean isDeleted;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, entity.getId());
            int i = ps.executeUpdate();
            isDeleted = (i > 0) ? true : false;
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", entity);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return isDeleted;
    }

    public static List getAll(String query, Connection connection, Class clazz) {
        List dictionaryList = new ArrayList();
        Statement statement = null;
        Long id;
        String value_ru;
        String value_en;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Object entity;
            while (rs.next()) {
                id = rs.getLong("id");
                value_ru = rs.getString("value_ru");
                value_en = rs.getString("value_en");

                Class c = Class.forName(clazz.getName());
                entity = c.getDeclaredConstructor(Long.class, String.class, String.class).newInstance(id, value_ru, value_en);

                dictionaryList.add(entity);
            }
        } catch (SQLException e) {
            log.error("Error while SQL query select");
            throw new JdbcDaoException("Unable to query SQL", e);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return dictionaryList;
    }
}
