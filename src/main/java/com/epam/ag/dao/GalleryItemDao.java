package com.epam.ag.dao;

import com.epam.ag.model.GalleryItem;

import java.util.List;

public interface GalleryItemDao extends GenericDao<GalleryItem> {
    @Override
    GalleryItem save(GalleryItem entity);

    @Override
    GalleryItem getByParameter(String param, String value);

    @Override
    GalleryItem getById(Long id);

    @Override
    List<GalleryItem> getAll();

    @Override
    boolean delete(GalleryItem entity);

    List<GalleryItem> getAllForGallery(Long id);

    void beginTransaction();

    void commit();

    void rollback();
}
