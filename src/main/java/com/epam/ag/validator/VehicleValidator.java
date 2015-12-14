package com.epam.ag.validator;

import com.epam.ag.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 * @deprecated
 */
public class VehicleValidator {
    public static List validate(Vehicle vehicle) {
        List<String> errors = new ArrayList<>();


        return errors;
    }

    public static String isManufacturIDValid(String login) {
        if (login.length() > 25) {
            return "error.login.too_long";
        }
        if (!login.matches("[A-Za-z0-9_]+")) {
            return "error.vehicle.model.incorrect_characters";
        }
        return null;
    }
}
