package com.epam.ag.action;

import com.epam.ag.service.ColorService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add new color
 *
 * @author Govorov Andrey
 */
public class AddColorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("color", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/color-form";
        }

        // It`s ok save it and redirect to list
        String colorRu = req.getParameter("color_ru");
        String colorEn = req.getParameter("color_en");
        ColorService colorService = new ColorService();
        boolean isAdded = colorService.addNewColor(colorRu, colorEn);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("color.save.fail", SystemMessage.ERROR));
            return "admin/color-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("color.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=color-list";
    }
}
