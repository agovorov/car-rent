package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
import com.epam.ag.dao.*;
import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.*;
import com.epam.ag.utils.DictionaryLoader;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by Govorov Andrey.
 */
public class VehicleUpdateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleId = Long.valueOf(req.getParameter("id"));

        DaoFactory daoFactory = DaoFactory.getInstance();

        // List of the manufactors
        DictionaryLoader.loadDictionaries(req);

        if (vehicleId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/vehicle-list";
        }
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        // Loading model
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = new Vehicle();
        vehicle = dao.getById(vehicleId);

        if (vehicle == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            return "admin/vehicle-list";
        }

        if (req.getMethod().equals("POST")) {
            vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);

            // Trying to save
            VehicleDao daoVehicle = daoFactory.getDao(VehicleDao.class);
            try {
                vehicle = daoVehicle.save(vehicle);
            } catch (Exception e) {
                // Error occurred
                req.setAttribute("systemMessage", new SystemMessage("Error found!", SystemMessage.ERROR));
                return "admin/vehicle-form";
            }
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-update&id=" + vehicle.getId();
        }

        req.setAttribute("vehicle", vehicle);
        return "admin/vehicle-form";
    }
}
