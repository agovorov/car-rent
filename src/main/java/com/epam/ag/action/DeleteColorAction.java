package com.epam.ag.action;

import com.epam.ag.service.ColorService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class DeleteColorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long colorId;
        try {
            colorId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }

        ColorService colorService = new ColorService();
        boolean isDeleted = colorService.deleteColorById(colorId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("color.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("color.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=color-list";
    }
}