package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.service.GearshiftService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ListVehicleGearAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        GearshiftService service = new GearshiftService();
        List<VehicleGearShift> gearShifts = service.getFuelTypeList();
        req.setAttribute("gearShifts", gearShifts);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/vehicle-gear-list";
    }
}
