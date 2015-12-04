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
        actions.put("GET/index", new IndexAction());
        actions.put("GET/error", new ErrorAction());
        actions.put("GET/vehicle-list", new ShowVehicleListAction());
        actions.put("GET/vehicle-create", new ShowVehicleCreateAction());
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

        if (!actions.containsKey(actionKey)) {
            log.warn("Action method not found.");
            return actions.get("GET/error");
        }
        return actions.get(actionKey);
    }
}
