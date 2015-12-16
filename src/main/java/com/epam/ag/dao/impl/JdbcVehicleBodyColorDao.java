package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JdbcVehicleBodyColorDao extends JdbcAbstractDao implements VehicleBodyColorDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleBodyColorDao.class);

    public JdbcVehicleBodyColorDao(Connection connection) {
        this.connection = connection;
        //pm.loadPropertyFile("query.properties");
    }

    @Override
    public VehicleBodyColor save(VehicleBodyColor entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    @Override
    public VehicleBodyColor getByParameter(String param, String value) {
        return null;
    }

    @Override
    public VehicleBodyColor getById(Long id) {
        log.trace("getById SQL statement: id={}", id);
        String query = pm.get("vehicleBodyColor.getbyid");
        return JdbcCommonDictDao.getById(query, connection, id, VehicleBodyColor.class);
    }

    @Override
    public List<VehicleBodyColor> getAll() {
        log.trace("getAll SQL statement");
        String query = pm.get("vehicleBodyColor.getAll");
        return JdbcCommonDictDao.getAll(query, connection, VehicleBodyColor.class);
    }

    @Override
    public boolean delete(VehicleBodyColor entity) {
        String query = pm.get("vehicleBodyColor.delete");
        log.trace("SQL delete statement: {}", entity);
        return JdbcCommonDictDao.delete(query, connection, entity);
    }

    private VehicleBodyColor update(VehicleBodyColor entity) {
        String query = pm.get("vehicleBodyColor.update");
        log.trace("SQL update statement: {}", entity);
        return JdbcCommonDictDao.update(query, connection, entity, VehicleBodyColor.class);
    }

    private VehicleBodyColor insert(VehicleBodyColor entity) {
        String query = pm.get("vehicleBodyColor.insert");
        log.trace("SQL insert statement: {}", entity);
        return JdbcCommonDictDao.insert(query, connection, entity, VehicleBodyColor.class);
    }
}
