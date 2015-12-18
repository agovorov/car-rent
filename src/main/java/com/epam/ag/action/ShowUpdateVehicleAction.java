package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Vehicle;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.DictionaryLoader;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowUpdateVehicleAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleId;
        try {
            vehicleId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-list";
        }

        // Get data for showing record
        VehicleService service = new VehicleService();
        Vehicle vehicle = service.getVehicle(vehicleId);
        if (vehicle == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-list";
        }

        DictionaryLoader.loadDictionaries(req);
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        req.setAttribute("vehicle", vehicle);
        return "admin/vehicle-form";
    }
}
