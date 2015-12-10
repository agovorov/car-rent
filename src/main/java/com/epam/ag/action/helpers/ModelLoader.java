package com.epam.ag.action.helpers;

import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.*;
import com.sun.deploy.net.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Govorov Andrey
 */
public class ModelLoader {

    private static final Logger log = LoggerFactory.getLogger(ModelLoader.class);

    public static Vehicle loadVehicleFromRequest(HttpServletRequest req, Vehicle vehicle) {
        Long manufacturerId;
        String model;

        // Read from session
        Locale userLocale = Locale.forLanguageTag("ru") ;
        NumberFormat formatter = NumberFormat.getNumberInstance(userLocale);

        log.trace("Getting data from request...");
        try {
            manufacturerId = Long.parseLong(req.getParameter("v-manufactor"));
            vehicle.setManufacturer(new VehicleManufacturer(manufacturerId));
            log.trace("Manufacturer ID done {}", manufacturerId);

            model = req.getParameter("v-model");
            vehicle.setModel(model);

            int year = Integer.parseInt(req.getParameter("v-year"));
            vehicle.setYear(year);

            if (req.getParameter("v-volume") != null) {
                String volumeString = req.getParameter("v-volume");
                Number volume = formatter.parse(volumeString);
                vehicle.setVolume(volume.floatValue());
                log.trace("volume: {} => {}", volumeString, volume.floatValue());
            }

            double consumption = Double.parseDouble(req.getParameter("v-consumpt"));
            vehicle.setConsumption(consumption);

            if (req.getParameter("v-price") != null) {
                String priceString = req.getParameter("v-price");
                Number volume = formatter.parse(priceString);
                vehicle.setPrice(volume.floatValue());
                log.trace("price: {} => {}", priceString, volume.floatValue());
            }

            long bodyTypeId = Integer.parseInt(req.getParameter("v-bodytype"));
            vehicle.setBodyType(new VehicleBodyType(bodyTypeId));

            long colorId = Integer.parseInt(req.getParameter("v-color"));
            vehicle.setColor(new VehicleBodyColor(colorId));

            long gearId = Integer.parseInt(req.getParameter("v-gear"));
            vehicle.setTransmission(new VehicleGearShift(gearId));

            long fuelId = Integer.parseInt(req.getParameter("v-fuel"));
            vehicle.setFuelType(new VehicleFuelType(fuelId));

            // !!!!!

            vehicle.setGallery(new Gallery(1L));
        } catch (Exception e) {
            log.error("Exception: ", e);
        }

        return vehicle;
    }
}
