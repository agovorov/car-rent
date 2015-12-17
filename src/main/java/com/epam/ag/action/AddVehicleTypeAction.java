package com.epam.ag.action;

import com.epam.ag.service.TypeService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class AddVehicleTypeAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("type", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-type-form";
        }

        // It`s ok save it and redirect to list
        String typeRu = req.getParameter("type_ru");
        String typeEn = req.getParameter("type_en");
        TypeService service = new TypeService();
        boolean isAdded = service.addNewBodyType(typeRu, typeEn);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("type.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-type-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("type.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-type-list";
    }
}
