package com.epam.ag.dao.impl;

import com.epam.ag.connection.ConnectionPool;
import com.epam.ag.dao.VehicleGearShiftDao;
import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JdbcVehicleGearShiftDao extends JdbcAbstractDao implements VehicleGearShiftDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleGearShiftDao.class);

    public JdbcVehicleGearShiftDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }


    @Override
    public VehicleGearShift save(VehicleGearShift entity) {
       return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private VehicleGearShift update(VehicleGearShift entity) {
        String query = pm.get("vehicleGearShift.update");
        log.trace("gear shift update statement: {}", entity);
        return JdbcCommonDictDao.update(query, connection, entity, VehicleGearShift.class);
    }

    private VehicleGearShift insert(VehicleGearShift entity) {
        String query = pm.get("vehicleGearShift.insert");
        log.trace("gear shift insert statement: {}", entity);
        return JdbcCommonDictDao.insert(query, connection, entity, VehicleGearShift.class);
    }

    @Override
    public VehicleGearShift getByParameter(String param, String value) {
        return null;
    }

    @Override
    public VehicleGearShift getById(Long id) {
        log.trace("getById statement: id={}", id);
        String query = pm.get("vehicleGearShift.getById");
        return JdbcCommonDictDao.getById(query, connection, id, VehicleGearShift.class);
    }

    @Override
    public List<VehicleGearShift> getAll() {
        log.trace("getAll statement");
        String query = pm.get("vehicleGearShift.getAll");
        return JdbcCommonDictDao.getAll(query,connection, VehicleGearShift.class);
    }

    @Override
    public boolean delete(VehicleGearShift entity) {
        log.trace("Delete statement: {}", entity);
        String query = pm.get("vehicleGearShift.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
