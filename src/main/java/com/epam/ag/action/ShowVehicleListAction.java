package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleListAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Display vehicle page with list of cars

        List<String> vehicle = new ArrayList<>();
        vehicle.add("Audi");
        vehicle.add("BMW");
        vehicle.add("Lexus");
        req.setAttribute("vehicles", vehicle);

        return "admin/vehicle-list";
    }
}
