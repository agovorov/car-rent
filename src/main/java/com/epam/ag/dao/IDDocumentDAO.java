package com.epam.ag.dao;

import com.epam.ag.model.user.IDDocument;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface IDDocumentDao extends GenericDao<IDDocument> {
    @Override
    IDDocument save(IDDocument entity);

    @Override
    IDDocument getByParameter(String param, String value);

    @Override
    IDDocument getById(Long id);

    @Override
    List<IDDocument> getAll();

    @Override
    boolean delete(IDDocument entity);
}
