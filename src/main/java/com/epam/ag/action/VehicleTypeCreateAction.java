package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleTypeCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String typeRu = req.getParameter("type-name-ru");
        String typeEn = req.getParameter("type-name-en");
        if (typeRu.isEmpty() || typeEn.isEmpty()) {
            req.setAttribute("systemMessage", new SystemMessage("Please, enter color`s name in both languages!", SystemMessage.ERROR));
            return "admin/vehicle-type-form";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = new VehicleBodyType(typeRu, typeEn);
        dao.save(vehicleBodyType);

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully created!", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-type-list";
    }
}
