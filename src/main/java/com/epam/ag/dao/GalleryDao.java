package com.epam.ag.dao;

import com.epam.ag.model.Gallery;

import java.util.List;

public interface GalleryDao extends GenericDao<Gallery> {
    @Override
    Gallery save(Gallery entity);

    @Override
    Gallery getByParameter(String param, String value);

    @Override
    Gallery getById(Long id);

    @Override
    List<Gallery> getAll();

    @Override
    boolean delete(Gallery entity);

    void beginTransaction();

    void commit();

    void rollback();
}
