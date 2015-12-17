package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.service.ColorService;
import com.epam.ag.service.TypeService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class UpdateVehicleTypeAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdateVehicleTypeAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleTypeId;
        try {
            vehicleTypeId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("type.form.wrong.id", SystemMessage.ERROR));
            return "admin/vehicle-type-list";
        }

        // Get data for showing record
        TypeService service = new TypeService();
        VehicleBodyType type = service.getBodyType(vehicleTypeId);
        if (type == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("type.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-type-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("type", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-type-form";
        }

        String typeRu = req.getParameter("type_ru");
        String typeEn = req.getParameter("type_en");
        boolean isSaved = service.updateBodyType(vehicleTypeId, typeRu, typeEn);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("type.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-type-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("type.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-type-update&id=" + vehicleTypeId;
    }
}
