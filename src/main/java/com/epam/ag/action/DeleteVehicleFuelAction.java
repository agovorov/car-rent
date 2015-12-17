package com.epam.ag.action;

import com.epam.ag.service.FuelTypeService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class DeleteVehicleFuelAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long fuelId;
        try {
            fuelId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }

        FuelTypeService service = new FuelTypeService();
        boolean isDeleted = service.deleteFuelTypeById(fuelId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-fuel-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("fuel.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-fuel-list";
    }
}
