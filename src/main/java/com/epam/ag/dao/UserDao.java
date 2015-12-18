package com.epam.ag.dao;

import com.epam.ag.model.User;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface UserDao extends GenericDao<User> {
    @Override
    User save(User entity);

    @Override
    User getByParameter(String param, String value);

    @Override
    User getById(Long id);

    @Override
    List<User> getAll();

    @Override
    boolean delete(User entity);

    User getByEmail(String login);
}
