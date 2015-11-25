package com.epam.ag.dao;

import com.epam.ag.model.Vehicle;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class VehicleDao implements GenericDao<Vehicle> {

    @Override
    public Vehicle save(Vehicle entity) {
        return null;
    }

    @Override
    public Vehicle getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Vehicle getById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }

    @Override
    public boolean delete(Vehicle entity) {
        return false;
    }

    //Create
    //Vehicle insert(Vehicle vehicle);

    // Read
    //Vehicle getById(Long id);
    //Vehicle getByParameter(String param, String value);

    // Update
    //Vehicle update(Vehicle vehicle);

    // Delete
    //boolean delete(Vehicle vehicle);
}
