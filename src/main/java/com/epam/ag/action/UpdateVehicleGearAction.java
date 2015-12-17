package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.service.GearshiftService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class UpdateVehicleGearAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long gearId;
        try {
            gearId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("gear.form.wrong.id", SystemMessage.ERROR));
            return "admin/vehicle-gear-form";
        }

        // Get data for showing record
        GearshiftService service = new GearshiftService();
        VehicleGearShift vehicleGearShift = service.getGearShift(gearId);
        if (vehicleGearShift == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("gear.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-gear-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("gearshift", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-gear-form";
        }

        String gearRu = req.getParameter("gearshift_ru");
        String rearEn = req.getParameter("gearshift_en");
        boolean isSaved = service.updateGearShift(gearId, gearRu, rearEn);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("gear.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-gear-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("gear.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-gear-update&id=" + vehicleGearShift.getId();
    }
}
