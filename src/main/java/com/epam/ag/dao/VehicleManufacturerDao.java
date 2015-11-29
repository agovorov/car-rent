package com.epam.ag.dao;

import com.epam.ag.model.dict.VehicleManufacturer;

import java.util.List;

/**
 * @author Govorov Andrey
 */

public interface VehicleManufacturerDao extends GenericDao<VehicleManufacturer> {
    @Override
    VehicleManufacturer save(VehicleManufacturer entity);

    @Override
    VehicleManufacturer getByParameter(String param, String value);

    @Override
    VehicleManufacturer getById(Long id);

    @Override
    List<VehicleManufacturer> getAll();

    @Override
    boolean delete(VehicleManufacturer entity);
}
