package com.epam.ag.dao;

import com.epam.ag.dao.GenericDao;
import com.epam.ag.model.dict.VehicleBodyType;

import java.util.List;

public interface VehicleBodyTypeDao extends GenericDao<VehicleBodyType> {
    @Override
    VehicleBodyType save(VehicleBodyType entity);

    @Override
    VehicleBodyType getByParameter(String param, String value);

    @Override
    VehicleBodyType getById(Long id);

    @Override
    List<VehicleBodyType> getAll();

    @Override
    boolean delete(VehicleBodyType entity);
}
