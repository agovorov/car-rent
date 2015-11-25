package com.epam.ag.dao;

import com.epam.ag.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleDao implements VehicleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleDao.class);

    @Override
    public Vehicle save(Vehicle entity) {
        log.trace("Save JDBC method");
        return null;
    }

    @Override
    public Vehicle getByParameter(String param, String value) {
        log.trace("Save JDBC getByParameter");
        return null;
    }

    @Override
    public Vehicle getById(Long id) {
        log.trace("Save JDBC getById");
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        log.trace("Save JDBC getAll");
        return null;
    }

    @Override
    public boolean delete(Vehicle entity) {
        log.trace("Save JDBC delete");
        return false;
    }
}
