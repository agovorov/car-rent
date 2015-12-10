package com.epam.ag.utils;

import com.epam.ag.dao.*;
import com.epam.ag.model.dict.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class DictionaryLoader {

    public static void loadDictionaries(HttpServletRequest session) {
        DaoFactory daoFactory = DaoFactory.getInstance();

        // List of the manufactors
        VehicleManufacturerDao manufacturerDao = daoFactory.getDao(VehicleManufacturerDao.class);
        List<VehicleManufacturer> manufacturers = manufacturerDao.getAll();
        session.setAttribute("manufactors", manufacturers);

        // List of body types
        VehicleBodyTypeDao vehicleBodyTypeDao = daoFactory.getDao(VehicleBodyTypeDao.class);
        List<VehicleBodyType> types = vehicleBodyTypeDao.getAll();
        session.setAttribute("types", types);

        // List of vehicle`s color
        VehicleBodyColorDao vehicleBodyColorDao = daoFactory.getDao(VehicleBodyColorDao.class);
        List<VehicleBodyColor> colors = vehicleBodyColorDao.getAll();
        session.setAttribute("colors", colors);

        // List of gearshits
        VehicleGearShiftDao vehicleGearShiftDao = daoFactory.getDao(VehicleGearShiftDao.class);
        List<VehicleGearShift> gearShifts = vehicleGearShiftDao.getAll();
        session.setAttribute("gearShifts", gearShifts);

        // List of fuel`s type
        VehicleFuelTypeDao vehicleFuelTypeDao = daoFactory.getDao(VehicleFuelTypeDao.class);
        List<VehicleFuelType> fuelList = vehicleFuelTypeDao.getAll();
        session.setAttribute("fuels", fuelList);
    }
}
