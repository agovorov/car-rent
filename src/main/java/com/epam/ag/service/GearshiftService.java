package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleGearShiftDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.dict.VehicleGearShift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class GearshiftService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(GearshiftService.class);

    public List<VehicleGearShift> getFuelTypeList() {
        daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        List<VehicleGearShift> list = dao.getAll();
        daoFactory.close();
        return list;
    }

    public boolean addNewGearshift(String fuelRu, String fuelEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleFuelType = new VehicleGearShift(fuelRu, fuelEn);
        boolean saveResult = save(vehicleFuelType, dao);
        return saveResult;
    }

    public boolean updateGearShift(Long id, String fuelRu, String fuelEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleFuelType = dao.getById(id);
        if (vehicleFuelType == null) return false;
        vehicleFuelType.setValues(fuelRu, fuelEn);
        boolean saveResult = save(vehicleFuelType, dao);
        return saveResult;
    }

    public VehicleGearShift getGearShift(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleFuelType = dao.getById(id);
        daoFactory.close();
        return vehicleFuelType;
    }

    public boolean deleteGearShiftById(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleFuelType = dao.getById(id);
        if (vehicleFuelType == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(vehicleFuelType);
        daoFactory.close();
        return isDeleted;
    }

    private boolean save(VehicleGearShift entity, VehicleGearShiftDao dao) {
        try {
            dao.save(entity);
        } catch (DaoException e) {
            log.error("Unable to save gearshift type", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }
}
