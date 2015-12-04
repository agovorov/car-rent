package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.Vehicle;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleDao implements VehicleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleDao.class);
    private Connection connection;
    private PropertiesManager pm;

    public JdbcVehicleDao(Connection connection) {
        this.connection = connection;
        pm = PropertiesManager.getInstance();
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicle.isPersisted() ? update(vehicle) : insert(vehicle);
    }

    private Vehicle update(Vehicle vehicle) {
        String query = pm.get("vehicle.update");

        log.trace("SQL update statement: {}", vehicle);
        PreparedStatement ps = null;
        try {
            // Old record - UPDATE query
            ps = connection.prepareStatement(query);
            ps.setLong(1, vehicle.getManufactorId());
            ps.setString(2, vehicle.getModelId());
            ps.setInt(3, vehicle.getYear());
            ps.setLong(4, vehicle.getColorId());
            ps.setLong(5, vehicle.getBodyType());
            ps.setLong(6, vehicle.getFuel());
            ps.setLong(7, vehicle.getGearShift());
            ps.setDouble(8, vehicle.getConsumption());
            ps.setDouble(9, vehicle.getVolume());
            ps.setDouble(10, vehicle.getPrice());
            ps.setLong(11, vehicle.getGalleryId());
            ps.setLong(12, vehicle.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while SQL query update", e);
        }
        return vehicle;
    }

    private Vehicle insert(Vehicle vehicle) {
        String query = pm.get("vehicle.insert");

        log.trace("SQL insert statement: {}", vehicle);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, vehicle.getManufactorId());
            ps.setString(2, vehicle.getModelId());
            ps.setInt(3, vehicle.getYear());
            ps.setLong(4, vehicle.getColorId());
            ps.setLong(5, vehicle.getBodyType());
            ps.setLong(6, vehicle.getFuel());
            ps.setLong(7, vehicle.getGearShift());
            ps.setDouble(8, vehicle.getConsumption());
            ps.setDouble(9, vehicle.getVolume());
            ps.setDouble(10, vehicle.getPrice());
            ps.setLong(11, vehicle.getGalleryId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            vehicle.setId(newId);
        } catch (SQLException e) {
            log.error("Error while SQL query update", e);
            throw new RuntimeException("Unable to query SQL", e);
        }

        // test connection close
        try {
            connection.close();
        } catch (SQLException e) {
            log.trace("Unable to close connection: {}", e);
        }

        return vehicle;
    }

    @Override
    public Vehicle getByParameter(String param, String value) {
        log.trace("JdbcVehicleDao getByParameter");
        return null;
    }

    @Override
    public Vehicle getById(Long id) {
        log.trace("Vehicle getById statement: {}", id);
        String query = pm.get("vehicle.getById");
        PreparedStatement ps;
        Vehicle vehicle = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setId(id);
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setConsumption(rs.getDouble("consumption"));
                vehicle.setVolume(rs.getDouble("volume"));
                vehicle.setPrice(rs.getDouble("price"));

                //
//                manufactor
//                color
//                body
//                        fueltype
//                gearshift
//                        gallery
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAll() {
        log.trace("JdbcVehicleDao getAll");
        return null;
    }

    @Override
    public boolean delete(Vehicle entity) {
        return false;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("begin transaction");
            throw new JdbcDaoException("Unable to begin transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("commit transaction");
            throw new JdbcDaoException("Unable to commit transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("rollback transaction");
            throw new JdbcDaoException("Unable to rollback transaction", e);
        }
    }
}
