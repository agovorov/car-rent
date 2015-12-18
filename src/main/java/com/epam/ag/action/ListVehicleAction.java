package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Vehicle;
import com.epam.ag.service.VehicleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ListVehicleAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        VehicleService service = new VehicleService();
        List<Vehicle> vehicles = service.getVehicleList();
        req.setAttribute("vehicles", vehicles);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/vehicle-list";
    }
}