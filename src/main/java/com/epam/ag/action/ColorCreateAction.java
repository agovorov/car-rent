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
public class ColorCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String colorRu = req.getParameter("color-name-ru");
        String colorEn = req.getParameter("color-name-en");
        if (colorRu.isEmpty() || colorEn.isEmpty()) {
            req.setAttribute("systemMessage", new SystemMessage("Please, enter color`s name in both languages!", SystemMessage.Type.ERROR));
            return "admin/color-form";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        VehicleBodyColor vehicleBodyColor = new VehicleBodyColor(colorRu, colorEn);
        dao.save(vehicleBodyColor);

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully created!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=color-list";
    }
}
