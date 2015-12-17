package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.dict.VehicleBodyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class TypeService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(TypeService.class);
    private DaoFactory daoFactory;

    public List<VehicleBodyType> getBodyTypeList() {
        daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        List<VehicleBodyType> list = dao.getAll();
        daoFactory.close();
        return list;
    }

    public boolean addNewBodyType(String typeRu, String typeEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = new VehicleBodyType(typeRu, typeEn);
        boolean saveResult = save(vehicleBodyType, dao);
        return saveResult;
    }

    public boolean updateBodyType(Long id, String typeRu, String typeEn) {
        daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = dao.getById(id);
        if (vehicleBodyType == null) return false;
        vehicleBodyType.setValues(typeRu, typeEn);
        boolean saveResult = save(vehicleBodyType, dao);
        return saveResult;
    }

    public VehicleBodyType getBodyType(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = dao.getById(id);
        daoFactory.close();
        return vehicleBodyType;
    }

    public boolean deleteBodyTypeById(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = dao.getById(id);
        if (vehicleBodyType == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(vehicleBodyType);
        daoFactory.close();
        return isDeleted;
    }

    private boolean save(VehicleBodyType entity, VehicleBodyTypeDao dao) {
        try {
            dao.save(entity);
        } catch (DaoException e) {
            log.error("Unable to add body type", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }
}
