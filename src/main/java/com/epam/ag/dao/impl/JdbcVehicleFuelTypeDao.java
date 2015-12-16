package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleFuelTypeDao;
import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JdbcVehicleFuelTypeDao extends JdbcAbstractDao implements VehicleFuelTypeDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleFuelTypeDao.class);

    public JdbcVehicleFuelTypeDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public VehicleFuelType save(VehicleFuelType entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private VehicleFuelType update(VehicleFuelType entity) {
        String query = pm.get("vehicleFuelType.update");
        log.trace("fuel type update query: {}", entity);
        return JdbcCommonDictDao.update(query, connection, entity, VehicleFuelType.class);
    }

    private VehicleFuelType insert(VehicleFuelType entity) {
        String query = pm.get("vehicleFuelType.insert");
        log.trace("fuel type insert query: {}", entity);
        return JdbcCommonDictDao.insert(query, connection, entity, VehicleFuelType.class);
    }

    @Override
    public VehicleFuelType getByParameter(String param, String value) {
        return null;
    }

    @Override
    public VehicleFuelType getById(Long id) {
        log.trace("getById SQL statement: id={}", id);
        String query = pm.get("vehicleFuelType.getbyid");
        return JdbcCommonDictDao.getById(query, connection, id, VehicleFuelType.class);
    }

    @Override
    public List<VehicleFuelType> getAll() {
        log.trace("getAll SQL statement");
        String query = pm.get("vehicleFuelType.getAll");
        return JdbcCommonDictDao.getAll(query, connection, VehicleFuelType.class);
    }

    @Override
    public boolean delete(VehicleFuelType entity) {
        String query = pm.get("vehicleFuelType.delete");
        log.trace("SQL delete statement: {}", entity);
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
