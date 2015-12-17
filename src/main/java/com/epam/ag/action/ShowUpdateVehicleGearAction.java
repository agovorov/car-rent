package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.service.GearshiftService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowUpdateVehicleGearAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long gearId;
        try {
            gearId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("gear.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-gear-list";
        }

        // Get data for showing record
        GearshiftService service = new GearshiftService();
        VehicleGearShift vehicleGearShift = service.getGearShift(gearId);
        if (vehicleGearShift == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("gear.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-gear-list";
        }
        req.setAttribute("model", vehicleGearShift);
        return "admin/vehicle-gear-form";
    }
}
