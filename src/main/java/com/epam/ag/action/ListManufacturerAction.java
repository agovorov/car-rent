package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.ManufacturerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class ListManufacturerAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Get list of manufacturers
        ManufacturerService service = new ManufacturerService();
        List<VehicleManufacturer> manufacturers = service.getManufacturerList();
        req.setAttribute("manufacturerList", manufacturers);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        return "admin/manufacturer-list";
    }
}
