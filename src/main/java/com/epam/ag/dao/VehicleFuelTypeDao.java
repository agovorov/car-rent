package com.epam.ag.dao;


import com.epam.ag.model.dict.VehicleFuelType;

import java.util.List;

public interface VehicleFuelTypeDao extends GenericDao<VehicleFuelType>{
    @Override
    VehicleFuelType save(VehicleFuelType entity);

    @Override
    VehicleFuelType getByParameter(String param, String value);

    @Override
    VehicleFuelType getById(Long id);

    @Override
    List<VehicleFuelType> getAll();

    @Override
    boolean delete(VehicleFuelType entity);
}
