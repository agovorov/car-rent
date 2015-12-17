package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleFuelTypeDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.dict.VehicleFuelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service for Vehicle Fuel Type
 *
 * @author Govorov Andrey
 */
public class FuelTypeService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(FuelTypeService.class);
    private DaoFactory daoFactory;

    public List<VehicleFuelType> getFuelTypeList() {
        daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        List<VehicleFuelType> list = dao.getAll();
        daoFactory.close();
        return list;
    }

    public boolean addNewFuelType(String fuelRu, String fuelEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = new VehicleFuelType(fuelRu, fuelEn);
        boolean saveResult = save(vehicleFuelType, dao);
        return saveResult;
    }

    public boolean updateFuelType(Long id, String fuelRu, String fuelEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = dao.getById(id);
        if (vehicleFuelType == null) return false;
        vehicleFuelType.setValues(fuelRu, fuelEn);
        boolean saveResult = save(vehicleFuelType, dao);
        return saveResult;
    }

    public VehicleFuelType getFuelType(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = dao.getById(id);
        daoFactory.close();
        return vehicleFuelType;
    }

    public boolean deleteFuelTypeById(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = dao.getById(id);
        if (vehicleFuelType == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(vehicleFuelType);
        daoFactory.close();
        return isDeleted;
    }

    private boolean save(VehicleFuelType entity, VehicleFuelTypeDao dao) {
        try {
            dao.save(entity);
        } catch (DaoException e) {
            log.error("Unable to save fuel type", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }
}
