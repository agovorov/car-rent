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


        actions.put("GET/vehicle-list", new ShowVehicleListAction());
        actions.put("GET/vehicle-create", new ShowPageAction("vehicle-create"));
        actions.put("POST/vehicle-create", new VehicleCreateAction());

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
        log.info("Action key: {}", actionKey);
        return actions.get(actionKey);
    }
}
