package com.epam.ag.action;

import com.epam.ag.service.TypeService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class DeleteVehicleTypeAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long typeId;
        try {
            typeId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-type-list";
        }

        TypeService service = new TypeService();
        boolean isDeleted = service.deleteBodyTypeById(typeId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-type-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("type.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-type-list";
    }
}
