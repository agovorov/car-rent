package com.epam.ag.action;

import com.epam.ag.service.GearshiftService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class AddVehicleGearShiftAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("gearshift", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-gear-form";
        }

        // It`s ok save it and redirect to list
        String gearShiftRu = req.getParameter("gearshift_ru");
        String gearShiftEn = req.getParameter("gearshift_en");
        GearshiftService service = new GearshiftService();
        boolean isAdded = service.addNewGearshift(gearShiftRu, gearShiftEn);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("gear.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-gear-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("gear.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-gear-list";
    }
}
