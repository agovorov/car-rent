package com.epam.ag.action;

import com.epam.ag.service.GearshiftService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class DeleteVehicleGearAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long gearId;
        try {
            gearId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("gear.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-gear-list";
        }

        GearshiftService service = new GearshiftService();
        boolean isDeleted = service.deleteGearShiftById(gearId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("gear.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-gear-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("gear.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-gear-list";
    }
}
