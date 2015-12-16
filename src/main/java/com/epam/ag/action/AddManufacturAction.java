package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.ManufacturerService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class AddManufacturAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("manufacturer", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/manufacturer-form";
        }

        // Validation complete... save it
        String manufacturer = req.getParameter("manufacturer");
        ManufacturerService service = new ManufacturerService();
        boolean isAdded = service.addNewManufacturer(manufacturer);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("manufacturer.save.fail", SystemMessage.ERROR));
            return "admin/manufacturer-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=manufacturer-list";
    }
}
