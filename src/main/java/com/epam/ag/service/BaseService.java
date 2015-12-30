package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;

/**
 * @author Govorov Andrey
 */
public abstract class BaseService {

    protected DaoFactory daoFactory;

    protected BaseService() {

    }

    protected BaseService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
