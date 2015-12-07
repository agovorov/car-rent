package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleFuelTypeDao;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleFuelCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String fuelRu = req.getParameter("fuel-name-ru");
        String fuelEn = req.getParameter("fuel-name-en");
        if (fuelRu.isEmpty() || fuelEn.isEmpty()) {
            req.setAttribute("systemMessage", new SystemMessage("Please, enter color`s name in both languages!", SystemMessage.Type.ERROR));
            return "admin/vehicle-fuel-form";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = new VehicleFuelType(fuelRu, fuelEn);
        dao.save(vehicleFuelType);

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully created!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=vehicle-fuel-list";
    }
}
