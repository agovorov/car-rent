package com.epam.ag.action;

import com.epam.ag.service.ManufacturerService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class DeleteManufacturAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long manufacturerId;
        try {
            manufacturerId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=manufacturer-list";
        }

        ManufacturerService service = new ManufacturerService();
        boolean isDeleted = service.deleteManufacturerById(manufacturerId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=manufacturer-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("manufacturer.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=manufacturer-list";
    }
}