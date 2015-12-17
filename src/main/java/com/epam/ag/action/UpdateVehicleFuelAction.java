package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.service.FuelTypeService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class UpdateVehicleFuelAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long fuelId;
        try {
            fuelId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("fuel.form.wrong.id", SystemMessage.ERROR));
            return "admin/vehicle-fuel-form";
        }

        // Get data for showing record
        FuelTypeService service = new FuelTypeService();
        VehicleFuelType vehicleFuelType = service.getFuelType(fuelId);
        if (vehicleFuelType == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("fuel", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-fuel-form";
        }

        String fuelRu = req.getParameter("fuel_ru");
        String fuelEn = req.getParameter("fuel_en");
        boolean isSaved = service.updateFuelType(fuelId, fuelRu, fuelEn);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("fuel.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-fuel-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-fuel-update&id=" + vehicleFuelType.getId();
    }
}
