package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class UpdateColorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long colorId = Long.valueOf(req.getParameter("id"));

        if (colorId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/color-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = new VehicleBodyColor();
        vehicleBodyColor = dao.getById(colorId);

        if (vehicleBodyColor == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            daoFactory.close();
            return "admin/color-list";
        }

        if (req.getMethod().equals("POST")) {
            String colorRu = req.getParameter("color-name-ru");
            String colorEn = req.getParameter("color-name-en");
            if (colorRu.isEmpty() || colorEn.isEmpty()) {
                req.setAttribute("vehicleColor", vehicleBodyColor);
                req.setAttribute("systemMessage", new SystemMessage("Please, enter color`s name in both languages!", SystemMessage.ERROR));
                daoFactory.close();
                return "admin/color-form";
            }

            // Save
            vehicleBodyColor.setValues(colorRu, colorEn);
            dao.save(vehicleBodyColor);
            req.setAttribute("systemMessage", "Record successfully saved.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            daoFactory.close();
            return "redirect:controller?action=color-update&id=" + vehicleBodyColor.getId();
        }

        req.setAttribute("vehicleColor", vehicleBodyColor);
        return "admin/color-form";
    }
}
