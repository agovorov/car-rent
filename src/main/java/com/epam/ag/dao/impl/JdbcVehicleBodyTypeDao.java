package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.dict.VehicleBodyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JdbcVehicleBodyTypeDao extends JdbcAbstractDao implements VehicleBodyTypeDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleBodyTypeDao.class);

    public JdbcVehicleBodyTypeDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }


    @Override
    public VehicleBodyType save(VehicleBodyType entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private VehicleBodyType update(VehicleBodyType entity) {
        String query = pm.get("vehicleBodyType.update");
        log.trace("body type update statement: {}", entity);
        return JdbcCommonDictDao.update(query, connection, entity, VehicleBodyType.class);
    }

    private VehicleBodyType insert(VehicleBodyType entity) {
        String query = pm.get("vehicleBodyType.insert");
        log.trace("body type insert statement: {}", entity);
        return JdbcCommonDictDao.insert(query, connection, entity, VehicleBodyType.class);
    }

    @Override
    public VehicleBodyType getByParameter(String param, String value) {
        return null;
    }

    @Override
    public VehicleBodyType getById(Long id) {
        log.trace("getById SQL statement: id={}", id);
        String query = pm.get("vehicleBodyType.getbyid");
        return JdbcCommonDictDao.getById(query, connection, id, VehicleBodyType.class);
    }

    @Override
    public List<VehicleBodyType> getAll() {
        log.trace("getAll SQL statement");
        String query = pm.get("vehicleBodyType.getAll");
        return JdbcCommonDictDao.getAll(query, connection, VehicleBodyType.class);
    }

    @Override
    public boolean delete(VehicleBodyType entity) {
        String query = pm.get("vehicleBodyType.delete");
        log.trace("SQL delete statement: {}", entity);
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
