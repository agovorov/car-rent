package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleManufacturerDao implements VehicleManufacturerDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleManufacturerDao.class);
    private Connection connection;
    private static PropertiesManager pm = PropertiesManager.getInstance();

    public JdbcVehicleManufacturerDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
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

        // test connection close
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleManufacturer;
    }

    @Override
    public List<VehicleManufacturer> getAll() {
        return null;
    }

    @Override
    public boolean delete(VehicleManufacturer entity) {
        String delete_query = pm.get("vehicleManufacturer.delete");
        log.trace("SQL delete statement: {}", entity);
        boolean isDeleted = JdbcCommonDictDao.delete(delete_query, connection, entity);
        return isDeleted;
    }

    private VehicleManufacturer update(VehicleManufacturer manufacturer) {
        // TODO надо убрать куда-то
        String update_query = pm.get("vehicleManufacturer.update");
        log.trace("SQL update statement: {}", manufacturer);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update_query);
            ps.setString(1, manufacturer.getValue());
            ps.setLong(2, manufacturer.getId ());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", manufacturer);
            throw new RuntimeException("Unable to query SQL", e);
        }
        return manufacturer;
    }

    private VehicleManufacturer insert(VehicleManufacturer manufacturer) {
        // TODO надя убрать куда-то
        String update_query = pm.get("vehicleManufacturer.insert");

        log.trace("SQL insert statement: {}", manufacturer);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update_query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manufacturer.getValue());
            int affectedRows = ps.executeUpdate();
            // TODO может вынести в утильный класс?
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Добавляем ключ
            Long newId = JdbcHelper.getReturningID(ps);
            manufacturer.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", manufacturer);
            throw new RuntimeException("Unable to query SQL", e);
        }
        return manufacturer;
    }
}
