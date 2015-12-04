package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "admin/vehicle-form-add";
    }
}
