package com.epam.ag.dao.impl;

import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public abstract class JdbcAbstractDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcAbstractDao.class);

    protected Connection connection;
    protected PropertiesManager pm;

    public JdbcAbstractDao() {
        pm = PropertiesManager.getInstance();
        pm.loadPropertyFile("query.properties");
    }
}