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
public class ColorDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long colorId = Long.valueOf(req.getParameter("id"));
        if (colorId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, wrong ID parameter!", SystemMessage.Type.ERROR));
            return "admin/color-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = new VehicleBodyColor();
        vehicleBodyColor = dao.getById(colorId);

        if (vehicleBodyColor == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry. Record not found!", SystemMessage.Type.ERROR));
            return "admin/color-list";
        }

        // Show confirm page
        if (req.getMethod().equalsIgnoreCase("GET")) {
            req.setAttribute("model", vehicleBodyColor);
            return "admin/color-delete";
        }

        // Do remove
        boolean isDeleted = dao.delete(vehicleBodyColor);
        if (!isDeleted) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, unable to delete.", SystemMessage.Type.ERROR));
            return "admin/color-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully deleted!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=color-list";
    }
}
