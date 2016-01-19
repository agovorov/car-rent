package com.epam.ag.action.helpers;

import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.VehicleException;
import com.epam.ag.model.dict.*;
import com.sun.deploy.net.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Vehicle model loader
 *
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
            manufacturerId = Long.parseLong(req.getParameter("manufactor"));
            vehicle.setManufacturer(new VehicleManufacturer(manufacturerId));
            log.trace("Manufacturer ID done {}", manufacturerId);

            model = req.getParameter("model");
            vehicle.setModel(model);

            int year = Integer.parseInt(req.getParameter("year"));
            vehicle.setYear(year);

            if (req.getParameter("volume") != null) {
                String volumeString = req.getParameter("volume");
                Number volume = formatter.parse(volumeString);
                vehicle.setVolume(volume.floatValue());
                log.trace("volume: {} => {}", volumeString, volume.floatValue());
            }

            int consumption = Integer.parseInt(req.getParameter("consumption"));
            vehicle.setConsumption(consumption);

            if (req.getParameter("price") != null) {
                String priceString = req.getParameter("price");
                Number volume = formatter.parse(priceString);
                vehicle.setPrice(volume.floatValue());
                log.trace("price: {} => {}", priceString, volume.floatValue());
            }

            long bodyTypeId = Integer.parseInt(req.getParameter("bodytype"));
            vehicle.setBodyType(new VehicleBodyType(bodyTypeId));

            long colorId = Integer.parseInt(req.getParameter("color"));
            vehicle.setColor(new VehicleBodyColor(colorId));

            long gearId = Integer.parseInt(req.getParameter("gear"));
            vehicle.setTransmission(new VehicleGearShift(gearId));

            long fuelId = Integer.parseInt(req.getParameter("fuel"));
            vehicle.setFuelType(new VehicleFuelType(fuelId));
        } catch (NumberFormatException | ParseException e) {
            log.error("Exception: ", e);
            throw new VehicleException("Unable to set data to model", e);
        }
        return vehicle;
    }
}