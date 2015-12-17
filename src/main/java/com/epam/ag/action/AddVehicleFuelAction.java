package com.epam.ag.action;

import com.epam.ag.service.FuelTypeService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class AddVehicleFuelAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("fuel", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-fuel-form";
        }

        // It`s ok save it and redirect to list
        String fuelRu = req.getParameter("fuel_ru");
        String fuelEn = req.getParameter("fuel_en");
        FuelTypeService service = new FuelTypeService();
        boolean isAdded = service.addNewFuelType(fuelRu, fuelEn);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("fuel.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-fuel-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-fuel-list";
    }
}
