package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.service.FuelTypeService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowUpdateVehicleFuelAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long fuelId;
        try {
            fuelId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }

        // Get data for showing record
        FuelTypeService service = new FuelTypeService();
        VehicleFuelType vehicleFuelType = service.getFuelType(fuelId);
        if (vehicleFuelType == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }
        req.setAttribute("model", vehicleFuelType);
        return "admin/vehicle-fuel-form";
    }
}
