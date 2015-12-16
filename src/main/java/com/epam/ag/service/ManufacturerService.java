package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.dict.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class ManufacturerService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(ManufacturerService.class);
    private DaoFactory daoFactory;

    public List<VehicleManufacturer> getManufacturerList() {
        daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        List<VehicleManufacturer> manufacturers = dao.getAll();
        daoFactory.close();
        return manufacturers;
    }

    public boolean addNewManufacturer(String manufacturer) {
        daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer(manufacturer);
        boolean saveResult = save(vehicleManufacturer, dao);
        //daoFactory.close();
        return saveResult;
    }

    public boolean updateManufacturer(Long manufacturerId, String manufacturer) {
        daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = dao.getById(manufacturerId);
        if (vehicleManufacturer == null) return false;
        vehicleManufacturer.setValues(manufacturer, null);
        boolean saveResult = save(vehicleManufacturer, dao);
       // daoFactory.close();
        return saveResult;
    }

    public VehicleManufacturer getManufacturer(Long manufacturerId) {
        daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = dao.getById(manufacturerId);
        daoFactory.close();
        return vehicleManufacturer;
    }

    public boolean deleteManufacturerById(Long manufacturerId) {
        daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = dao.getById(manufacturerId);
        if (vehicleManufacturer == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(vehicleManufacturer);
        daoFactory.close();
        return isDeleted;
    }

    private boolean save(VehicleManufacturer entity, VehicleManufacturerDao dao) {
        try {
            dao.save(entity);
        } catch (DaoException e) {
            log.error("Unable to add manufacturer", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }
}
