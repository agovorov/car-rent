package com.epam.ag.dao.impl;

import com.epam.ag.connection.ConnectionPool;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Vehicle;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleDao implements VehicleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleDao.class);
    private Connection connection = null;

    @Override
    public Vehicle save(Vehicle vehicle) {
        // Read queries from property file
        PropertiesManager pm = new PropertiesManager();
        String insert_query = pm.get("query.properties", "vehicle.insert");
        String update_query = pm.get("query.properties", "vehicle.update");

        log.trace("JdbcVehicleDao save");
        connection = ConnectionPool.getConnection();

        PreparedStatement ps = null;
        try {
            // manufacturer, model, year, color, body, fueltype, gearshift, consumption, volume
            // Примитивная версия
            if (vehicle.isNewRecord()) {
                // New record - INSERT query
                ps = connection.prepareStatement(insert_query);
                ps.setLong(1, vehicle.getManufactorId());
                ps.setString(2, vehicle.getModelId());
                ps.setInt(3, vehicle.getYear());
                ps.setLong(4, vehicle.getColorId());
                ps.setLong(5, vehicle.getBodyType());
                ps.setLong(6, vehicle.getFuel());
                ps.setLong(7, vehicle.getGearShift());
                ps.setDouble (8, vehicle.getConsumption());
                ps.setDouble(9, vehicle.getVolume());
            } else {
                // Old record - UPDATE query
                ps = connection.prepareStatement(update_query);
                ps.setLong(1, vehicle.getManufactorId());
                ps.setString(2, vehicle.getModelId());
                ps.setInt(3, vehicle.getYear());
                ps.setLong(4, vehicle.getColorId());
                ps.setLong(5, vehicle.getBodyType());
                ps.setLong(6, vehicle.getFuel());
                ps.setLong(7, vehicle.getGearShift());
                ps.setDouble (8, vehicle.getConsumption());
                ps.setDouble(9, vehicle.getVolume());
                ps.setLong(10, vehicle.getId());
            }
            ps.executeUpdate();

            // Updating ID
            long recordId = getInsertedId(ps);
            vehicle.setId(recordId);
        } catch (SQLException e) {
            log.error("Error while SQL query update", e);
        }

        // Незнаю когда закрыть, сделаю сейчас
        ConnectionPool.closeConnection(connection);
        return vehicle;
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

    private Long getInsertedId(PreparedStatement statement) {
        long id = 0;
        ResultSet rs = null;
        try {
            rs = statement.getGeneratedKeys();
            if (rs.next())  {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
