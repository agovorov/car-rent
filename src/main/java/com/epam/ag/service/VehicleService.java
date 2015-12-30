package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.VehicleBodyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public class VehicleService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

    public List<Vehicle> getVehicleList() {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        List<Vehicle> list = dao.getAll();
        daoFactory.close();
        return list;
    }

    public boolean addNewVehicle(Vehicle vehicle) {
        daoFactory = DaoFactory.getInstance();
        try {
            Gallery gallery = vehicle.getVehicleImages();

            // Save gallery first and return it`s ID
            GalleryService gs = new GalleryService();
            gallery = gs.save(gallery, false);

            // Now save vehicle model
            VehicleDao dao = daoFactory.getDao(VehicleDao.class);
            vehicle = dao.save(vehicle);

        } catch (Exception e) {
            log.error("Unable to save model Vehicle: {}", vehicle, e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public Vehicle getVehicleById(Long vehicleId) {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = dao.getById(vehicleId);
        daoFactory.close();
        return vehicle;
    }

    public boolean updateVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        try {
             dao.save(vehicle);
        } catch (Exception e) {
            log.error("We have got an exception", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public boolean deleteVehicleById(Long id) {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = dao.getById(id);
        if (vehicle == null) {
            daoFactory.close();
            return false;
        }

        try {
            dao.delete(vehicle);
        } catch (Exception e) {
            log.error("We have got an exception", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }
}
