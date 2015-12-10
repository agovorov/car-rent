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

        // Manufacturer
        actions.put("GET/manufacturers-list", new ShowManufacturerListAction());
        actions.put("GET/manufacturer-create", new ShowPageAction("/admin/manufacturer-form"));
        actions.put("POST/manufacturer-create", new ManufacturerAddAction());
        actions.put("GET/manufacturer-update", new ManufacturerUpdateAction());
        actions.put("POST/manufacturer-update", new ManufacturerUpdateAction());
        actions.put("GET/manufacturer-delete", new ManufacturerDeleteAction());
        actions.put("POST/manufacturer-delete", new ManufacturerDeleteAction());

        // Colors
        actions.put("GET/color-list", new ShowColorListAction());
        actions.put("GET/color-create", new ShowPageAction("/admin/color-form"));
        actions.put("POST/color-create", new ColorCreateAction());
        actions.put("GET/color-update", new ColorUpdateAction());
        actions.put("POST/color-update", new ColorUpdateAction());
        actions.put("GET/color-delete", new ColorDeleteAction());
        actions.put("POST/color-delete", new ColorDeleteAction());

        // Body types
        actions.put("GET/vehicle-type-list", new ShowVehicleTypeListAction());
        actions.put("GET/vehicle-type-create", new ShowPageAction("/admin/vehicle-type-form"));
        actions.put("POST/vehicle-type-create", new VehicleTypeCreateAction());
        actions.put("GET/vehicle-type-update", new VehicleTypeUpdateAction());
        actions.put("POST/vehicle-type-update", new VehicleTypeUpdateAction());
        actions.put("GET/vehicle-type-delete", new VehicleTypeDeleteAction());
        actions.put("POST/vehicle-type-delete", new VehicleTypeDeleteAction());

        // Fuel type
        actions.put("GET/vehicle-fuel-list", new ShowVehicleFuelListAction());
        actions.put("GET/vehicle-fuel-create", new ShowPageAction("/admin/vehicle-fuel-form"));
        actions.put("POST/vehicle-fuel-create", new VehicleFuelCreateAction());
        actions.put("GET/vehicle-fuel-update", new VehicleFuelUpdateAction());
        actions.put("POST/vehicle-fuel-update", new VehicleFuelUpdateAction());
        actions.put("GET/vehicle-fuel-delete", new VehicleFuelDeleteAction());
        actions.put("POST/vehicle-fuel-delete", new VehicleFuelDeleteAction());

        // Gearshift type
        actions.put("GET/vehicle-gear-list", new ShowVehicleGearListAction());
        actions.put("GET/vehicle-gear-create", new ShowPageAction("/admin/vehicle-gear-form"));
        actions.put("POST/vehicle-gear-create", new VehicleGearShiftCreateAction());
        actions.put("GET/vehicle-gear-update", new VehicleGearUpdateAction());
        actions.put("POST/vehicle-gear-update", new VehicleGearUpdateAction());
        actions.put("GET/vehicle-gear-delete", new VehicleGearDeleteAction());
        actions.put("POST/vehicle-gear-delete", new VehicleGearDeleteAction());


        // Vehicle
        actions.put("GET/vehicle-list", new ShowVehicleListAction());
        actions.put("GET/vehicle-create", new VehicleCreateAction());
        actions.put("POST/vehicle-create", new VehicleCreateAction());
        actions.put("GET/vehicle-update", new VehicleUpdateAction());
        actions.put("POST/vehicle-update", new VehicleUpdateAction());

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
