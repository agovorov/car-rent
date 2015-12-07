package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleTypeUpdateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VehicleTypeUpdateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleTypeId = Long.valueOf(req.getParameter("id"));

        if (vehicleTypeId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.Type.ERROR));
            return "admin/vehicle-type-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = new VehicleBodyType();
        vehicleBodyType = dao.getById(vehicleTypeId);

        if (vehicleBodyType == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.Type.ERROR));
            return "admin/vehicle-type-list";
        }

        if (req.getMethod().equals("POST")) {
            String typeRu = req.getParameter("type-name-ru");
            String typeEn = req.getParameter("type-name-en");
            if (typeRu.isEmpty() || typeEn.isEmpty()) {
                req.setAttribute("vehicleBodyType", vehicleBodyType);
                req.setAttribute("systemMessage", new SystemMessage("Please, enter vehicle`s type name in both languages!", SystemMessage.Type.ERROR));
                return "admin/vehicle-type-form";
            }

            // Save
            vehicleBodyType.setValues(typeRu, typeEn);
            dao.save(vehicleBodyType);
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.Type.SUCCESS));
            return "redirect:controller?action=vehicle-type-update&id=" +vehicleBodyType.getId();
        }

        log.trace("Model: {}", vehicleBodyType);
        req.setAttribute("vehicleBodyType", vehicleBodyType);
        return "admin/vehicle-type-form";
    }
}
