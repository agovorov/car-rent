package com.epam.ag.service;

import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Vehicle;

/**
 * @author Govorov Andrey
 */
public class VehicleService extends BaseService {

    public Vehicle getEntireEntity(Long id) {
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = dao.getById(id);

        // Load manufactor


        // Loading items
//        GalleryItemDao itemDao = daoFactory.getDao(GalleryItemDao.class);
//        GalleryItem item = null;
//        List<GalleryItem> items = itemDao.getAllForGallery(id);
//        gallery.addItems(items);

        return vehicle;
    }
}
