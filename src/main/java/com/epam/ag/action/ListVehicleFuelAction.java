package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.service.FuelTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ListVehicleFuelAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FuelTypeService service = new FuelTypeService();
        List<VehicleFuelType> fuels = service.getFuelTypeList();
        req.setAttribute("fuels", fuels);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/vehicle-fuel-list";
    }
}
