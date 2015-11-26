package com.epam.ag.dao.impl;

import com.epam.ag.connection.ConnectionPool;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleDao implements VehicleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleDao.class);
    private final Connection connection = null;
    private String INSERT_VEHICLE_SQL = "INSERT INTO vehicle (name, marka) VALUES (?, ?)";

    @Override
    public Vehicle save(Vehicle entity) {
        // propertyManager.getProperty("vehicle.update")
        log.trace("JdbcVehicleDao save");
        Connection connection = ConnectionPool.getConnection();
        /*
        PreparedStatement ps = connection.prepareStatement(INSERT_VEHICLE_SQL);
        ps.setString(1, "BMW");
        ps.setString(2, "X5");
        ps.executeUpdate();
        */
        return entity;
    }

    @Override
    public Vehicle getByParameter(String param, String value) {
        log.trace("JdbcVehicleDao getByParameter");
        return null;
    }

    @Override
    public Vehicle getById(Long id) {
        log.trace("JdbcVehicleDao getById");
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        log.trace("JdbcVehicleDao getAll");
        return null;
    }

    @Override
    public boolean delete(Vehicle entity) {
        log.trace("JdbcVehicleDao delete");
        return false;
    }
}
