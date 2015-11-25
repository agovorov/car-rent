package com.epam.ag.dao;

import com.epam.ag.model.BaseEntity;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface GenericDao<T extends BaseEntity> {
    // Create + Update
    T save(T entity);

    // Read
    T getByParameter(String param, String value);
    T getById(Long id);
    List<T> getAll();

    // Delete
    boolean delete(T entity);
}
