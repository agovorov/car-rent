package com.epam.ag.action;

import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class DeleteVehicleAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleId;
        try {
            vehicleId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }

        VehicleService service = new VehicleService();
        boolean isDeleted = service.deleteVehicleById(vehicleId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-list";
    }
}
