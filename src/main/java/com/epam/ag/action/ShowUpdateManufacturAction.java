package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.ManufacturerService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class ShowUpdateManufacturAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long manufacturerId;
        try {
            manufacturerId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=manufacturer-list";
        }

        // Get data
        ManufacturerService service = new ManufacturerService();
        VehicleManufacturer vehicleManufacturer = service.getManufacturer(manufacturerId);
        if (vehicleManufacturer == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=manufacturer-list";
        }

        req.setAttribute("model", vehicleManufacturer);
        return "admin/manufacturer-form";
    }
}
