package com.epam.ag.dao.impl;

import com.epam.ag.dao.GenericDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JdbcAbstractDao implements GenericDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcAbstractDao.class);
    private Connection connection;
    private PropertiesManager pm;

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // Не получилось, ну и не надо...
        }
    }



}
