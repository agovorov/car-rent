package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;

/**
 * @author Govorov Andrey
 */
public class BaseService {

    protected static final DaoFactory daoFactory = DaoFactory.getInstance();

}
