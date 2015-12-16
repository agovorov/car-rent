package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.service.ColorService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowUpdateColorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long colorId;
        try {
            colorId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }

        // Get data for showing record
        ColorService colorService = new ColorService();
        VehicleBodyColor vehicleBodyColor = colorService.getColor(colorId);
        if (vehicleBodyColor == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }
        req.setAttribute("model", vehicleBodyColor);
        return "admin/color-form";
    }
}
