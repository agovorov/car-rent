package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.ColorService;
import com.epam.ag.service.ManufacturerService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class UpdateManufacturAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long manufacturerId;
        try {
            manufacturerId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("manufacturer.form.wrong.id", SystemMessage.ERROR));
            return "admin/manufacturer-form";
        }

        ManufacturerService service = new ManufacturerService();
        VehicleManufacturer vehicleManufacturer = service.getManufacturer(manufacturerId);
        if (vehicleManufacturer == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=manufacturer-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("manufacturer", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/manufacturer-form";
        }

        String manufacturer = req.getParameter("manufacturer");
        boolean isSaved = service.updateManufacturer(manufacturerId, manufacturer);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("manufacturer.save.fail", SystemMessage.ERROR));
            return "admin/manufacturer-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=manufacturer-update&id=" + vehicleManufacturer.getId();
    }
}
