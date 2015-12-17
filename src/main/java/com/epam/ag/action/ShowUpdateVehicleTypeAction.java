package com.epam.ag.action;

import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.service.TypeService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class ShowUpdateVehicleTypeAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long typeId;
        try {
            typeId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-type-list";
        }

        // Get data for showing record
        TypeService service = new TypeService();
        VehicleBodyType type = service.getBodyType(typeId);
        if (type == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-type-list";
        }
        req.setAttribute("model", type);
        return "admin/vehicle-type-form";
    }
}
