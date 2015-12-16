package com.epam.ag.service;

import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;

/**
 * @author Govorov Andrey
 */
public class VehicleService extends BaseService {

    public Vehicle save(Vehicle vehicle) throws VehicleServiceException {

        Gallery gallery = vehicle.getVehicleImages();

        // Save gallery first and return it`s ID
        GalleryService gs = new GalleryService();
        gallery = gs.save(gallery, false);

        // Now save vehicle model
//        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
//        vehicle = dao.save(vehicle);

        return vehicle;
    }
}
