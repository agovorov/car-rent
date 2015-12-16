package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.dict.VehicleBodyColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ColorService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(ColorService.class);

    public List<VehicleBodyColor> getColorsList() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        List<VehicleBodyColor> colors = dao.getAll();
        daoFactory.close();

        return colors;
    }

    public boolean addNewColor(String colorRu, String colorEn) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = new VehicleBodyColor(colorRu, colorEn);
        // TODO вынестси, убрать дубль
        try {
            dao.save(vehicleBodyColor);
        } catch (DaoException e) {
            log.error("Unable to add color", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public VehicleBodyColor getColor(Long colorId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = dao.getById(colorId);
        daoFactory.close();

        log.trace("BodyColor: {}", vehicleBodyColor);
        return vehicleBodyColor;
    }

    public boolean updateColor(Long colorId, String colorRu, String colorEn) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = dao.getById(colorId);
        if (vehicleBodyColor == null) return false;
        vehicleBodyColor.setValues(colorRu, colorEn);

        try {
            dao.save(vehicleBodyColor);
        } catch (DaoException e) {
            log.error("Unable to add color", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public boolean deleteColorById(Long colorId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = dao.getById(colorId);
        if (vehicleBodyColor == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(vehicleBodyColor);
        daoFactory.close();
        return isDeleted;
    }
}
