package com.epam.ag.service;

import com.epam.ag.action.FilterVehicleAction;
import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.utils.SqlParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Govorov Andrey
 */
public class VehicleService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

    /**
     * Return list of vehicles
     *
     * @return List
     */
    public List<Vehicle> getVehicleList() {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        List<Vehicle> list = dao.getAll();
        daoFactory.close();
        return list;
    }

    /**
     * Add new vehicle and gallery (if uploaded)
     *
     * @param vehicle
     * @return
     */
    public boolean addNewVehicle(Vehicle vehicle) {
        daoFactory = DaoFactory.getInstance();
        try {
            Gallery gallery = vehicle.getVehicleImages();

            if (gallery != null) {
                // Save gallery first and return it`s ID
                GalleryService gs = new GalleryService();
                gallery = gs.save(gallery, false);
            }

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

    /**
     * Get vehicle info by ID
     *
     * @param vehicleId
     * @return
     */
    public Vehicle getVehicleById(Long vehicleId) {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = dao.getById(vehicleId);
        daoFactory.close();
        return vehicle;
    }

    /**
     * Save vehicle`s info
     *
     * @param vehicle
     * @return
     */
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

    public List<Vehicle> getVehicleList(Map params) {
        daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);

        List<Vehicle> list = dao.getAllByParameters(params);
        daoFactory.close();
        return list;
    }

    /**
     * Return list af all available vehicle
     *
     * @param startDate when user need a vehicle
     * @param endDate   until user need a vehicle
     * @param filter
     * @return
     */
    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate, FilterVehicleAction.VehicleFilter filter) {
        Map<String, Object> map = new HashMap<>();

        if (filter != null) {
            map.put("manufacturer_id", new SqlParams(filter.manufacturers));
            map.put("price", new SqlParams(filter.minPrice, filter.maxPrice));
        }

        List<Vehicle> vehicleList = getVehicleList(map);

        // We need short list for ajax response
        List rootList = new ArrayList();
        if (vehicleList.isEmpty()) {
            return rootList;
        }

        for (Vehicle vehicle : vehicleList) {
            Map<String, Object> childList = new HashMap<>();
            childList.put("id", vehicle.getId());
            childList.put("name", vehicle.getFullName());
            childList.put("price", vehicle.getPrice());
            childList.put("gearshift", vehicle.getVehicleGearShift().getValue());
            childList.put("consumption", vehicle.getConsumption());
            childList.put("volume", vehicle.getVolume());
            childList.put("img", vehicle.getGalleryId());
            rootList.add(childList);
        }
        return rootList;
    }
}
