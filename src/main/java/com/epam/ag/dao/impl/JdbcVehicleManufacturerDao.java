package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.dict.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleManufacturerDao extends JdbcAbstractDao implements VehicleManufacturerDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleManufacturerDao.class);

    public JdbcVehicleManufacturerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public VehicleManufacturer save(VehicleManufacturer entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    @Override
    public VehicleManufacturer getByParameter(String param, String value) {
        return null;
    }

    @Override
    public VehicleManufacturer getById(Long id) {
        log.trace("getById SQL statement: id={}", id);
        String insert_query = pm.get("vehicleManufacturer.getbyid");

        PreparedStatement ps = null;
        VehicleManufacturer vehicleManufacturer = null;
        try {
            String manufacturer = null;
            ps = connection.prepareStatement(insert_query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                manufacturer = rs.getString("manufacturer");
            }

            // Создаем сущность
            // TODO Можно ли тут создавать сущность?
            vehicleManufacturer = new VehicleManufacturer(id, manufacturer);
        } catch (SQLException e) {
            log.error("Error while SQL query update", e);
        }
        return vehicleManufacturer;
    }

    @Override
    public List<VehicleManufacturer> getAll() {
        log.trace("manufacturer getAll");
        String query = pm.get("vehicleManufacturer.getAll");
        List<VehicleManufacturer> manufacturerList = new ArrayList();
        Statement statement = null;
        VehicleManufacturer manufacturer;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                manufacturer = new VehicleManufacturer(
                        rs.getLong("id"),
                        rs.getString("manufacturer")
                );
                manufacturerList.add(manufacturer);
            }
        } catch (SQLException e) {
            log.error("Error while SQL query select {}", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return manufacturerList;
    }

    @Override
    public boolean delete(VehicleManufacturer entity) {
        String delete_query = pm.get("vehicleManufacturer.delete");
        log.trace("SQL delete statement: {}", entity);
        boolean isDeleted = JdbcCommonDictDao.delete(delete_query, connection, entity);
        return isDeleted;
    }

    private VehicleManufacturer update(VehicleManufacturer manufacturer) {
        String update_query = pm.get("vehicleManufacturer.update");
        log.trace("SQL update statement: {}", manufacturer);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update_query);
            ps.setString(1, manufacturer.getValue());
            ps.setLong(2, manufacturer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", manufacturer);
            throw new RuntimeException("Unable to query SQL", e);
        }
        return manufacturer;
    }

    private VehicleManufacturer insert(VehicleManufacturer manufacturer) {
        String update_query = pm.get("vehicleManufacturer.insert");

        log.trace("SQL insert statement: {}", manufacturer);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update_query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manufacturer.getValue());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Добавляем ключ
            log.trace("PS: {}", ps);
            long newId = JdbcHelper.getReturningID(ps);
            log.trace("New ID: {}", newId);
            manufacturer.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", manufacturer);
            throw new RuntimeException("Unable to query SQL", e);
        }
        return manufacturer;
    }
}
