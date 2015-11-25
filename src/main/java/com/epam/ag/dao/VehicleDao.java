package com.epam.ag.dao;

import com.epam.ag.model.Vehicle;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface VehicleDao extends GenericDao<Vehicle> {

    @Override
    Vehicle save(Vehicle entity);

    @Override
    Vehicle getByParameter(String param, String value);

    @Override
    Vehicle getById(Long id);

    @Override
    List<Vehicle> getAll();

    @Override
    boolean delete(Vehicle entity);
}
