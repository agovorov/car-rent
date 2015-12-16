package com.epam.ag.dao;

import com.epam.ag.dao.impl.exception.DaoFactoryException;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.model.dict.VehicleManufacturer;

import java.util.List;

public interface VehicleBodyColorDao extends GenericDao<VehicleBodyColor> {

    @Override
    VehicleBodyColor save(VehicleBodyColor entity) throws DaoFactoryException;

    @Override
    VehicleBodyColor getByParameter(String param, String value);

    @Override
    VehicleBodyColor getById(Long id);

    @Override
    List<VehicleBodyColor> getAll();

    @Override
    boolean delete(VehicleBodyColor entity);
}
