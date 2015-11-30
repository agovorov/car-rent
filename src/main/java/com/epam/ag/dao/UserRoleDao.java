package com.epam.ag.dao;

import com.epam.ag.model.user.UserRole;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface UserRoleDao extends GenericDao<UserRole> {
    @Override
    UserRole save(UserRole entity);

    @Override
    UserRole getByParameter(String param, String value);

    @Override
    UserRole getById(Long id);

    @Override
    List<UserRole> getAll();

    @Override
    boolean delete(UserRole entity);
}