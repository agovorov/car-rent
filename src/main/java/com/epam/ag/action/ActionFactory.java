package com.epam.ag.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public class ActionFactory {

    private static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/index", new ShowPageAction("index"));

        // Colors
        actions.put("GET/color-list", new ListColorAction());
        actions.put("GET/color-create", new ShowPageAction("/admin/color-form"));
        actions.put("POST/color-create", new AddColorAction());
        actions.put("GET/color-update", new ShowUpdateColorAction());
        actions.put("POST/color-update", new UpdateColorAction());
        actions.put("GET/color-delete", new DeleteColorAction());

        // Manufacturer
        actions.put("GET/manufacturer-list", new ListManufacturerAction());
        actions.put("GET/manufacturer-create", new ShowPageAction("/admin/manufacturer-form"));
        actions.put("POST/manufacturer-create", new AddManufacturAction());
        actions.put("GET/manufacturer-update", new ShowUpdateManufacturAction());
        actions.put("POST/manufacturer-update", new UpdateManufacturAction());
        actions.put("GET/manufacturer-delete", new DeleteManufacturAction());

        // Body types
        actions.put("GET/vehicle-type-list", new ListVehicleTypeAction());
        actions.put("GET/vehicle-type-create", new ShowPageAction("/admin/vehicle-type-form"));
        actions.put("POST/vehicle-type-create", new AddVehicleTypeAction());
        actions.put("GET/vehicle-type-update", new ShowUpdateVehicleTypeAction());
        actions.put("POST/vehicle-type-update", new UpdateVehicleTypeAction());
        actions.put("GET/vehicle-type-delete", new DeleteVehicleTypeAction());

        // Fuel type
        actions.put("GET/vehicle-fuel-list", new ListVehicleFuelAction());
        actions.put("GET/vehicle-fuel-create", new ShowPageAction("/admin/vehicle-fuel-form"));
        actions.put("POST/vehicle-fuel-create", new AddVehicleFuelAction());
        actions.put("GET/vehicle-fuel-update", new ShowUpdateVehicleFuelAction());
        actions.put("POST/vehicle-fuel-update", new UpdateVehicleFuelAction());
        actions.put("GET/vehicle-fuel-delete", new DeleteVehicleFuelAction());

        // Gearshift type
        actions.put("GET/vehicle-gear-list", new ListVehicleGearAction());
        actions.put("GET/vehicle-gear-create", new ShowPageAction("/admin/vehicle-gear-form"));
        actions.put("POST/vehicle-gear-create", new AddVehicleGearShiftAction());
        actions.put("GET/vehicle-gear-update", new ShowUpdateVehicleGearAction());
        actions.put("POST/vehicle-gear-update", new UpdateVehicleGearAction());
        actions.put("GET/vehicle-gear-delete", new DeleteVehicleGearAction());


        // Vehicle
        actions.put("GET/vehicle-list", new ListVehicleAction());
        actions.put("GET/vehicle-create", new AddVehicleFormAction());
        actions.put("POST/vehicle-create", new AddVehicleAction());
        actions.put("GET/vehicle-update", new ShowUpdateVehicleAction());
        actions.put("POST/vehicle-update", new UpdateVehicleAction());
        actions.put("GET/vehicle-delete", new DeleteVehicleAction());

        // User
        actions.put("GET/user-list", new ListUserAction());
        actions.put("GET/user-create", new AddUserFormAction());
        actions.put("POST/user-create", new AddUserAction());
        actions.put("GET/user-update", new ShowUpdateUserAction());
        actions.put("POST/user-update", new UpdateUserAction());
        actions.put("GET/user-delete", new DeleteUserAction());

        /*
        actions.put("GET/register", new DisplayPageAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        */
    }

    public Action getAction(HttpServletRequest request) {
        String actionName = request.getParameter("action");
        String method = request.getMethod();
        String actionKey = method + "/" + actionName;
        log.trace("Action key: {}", actionKey);
        return actions.get(actionKey);
    }
}
