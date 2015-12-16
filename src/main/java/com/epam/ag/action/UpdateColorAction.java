package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.service.ColorService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class UpdateColorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long colorId;
        try {
            colorId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("color.form.wrong.id", SystemMessage.ERROR));
            return "admin/color-form";
        }

        // Get data for showing record
        ColorService colorService = new ColorService();
        VehicleBodyColor vehicleBodyColor = colorService.getColor(colorId);
        if (vehicleBodyColor == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("color", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/color-form";
        }

        String colorRu = req.getParameter("color_ru");
        String colorEn = req.getParameter("color_en");
        boolean isSaved = colorService.updateColor(colorId, colorRu, colorEn);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("color.save.fail", SystemMessage.ERROR));
            return "admin/color-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("color.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=color-update&id=" + vehicleBodyColor.getId();
    }
}