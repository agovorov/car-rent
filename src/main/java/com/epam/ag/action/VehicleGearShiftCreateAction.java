package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleGearShiftDao;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleGearShiftCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String gearRu = req.getParameter("gear-name-ru");
        String gearEn = req.getParameter("gear-name-en");
        if (gearRu.isEmpty() || gearEn.isEmpty()) {
            req.setAttribute("systemMessage", new SystemMessage("Please, enter color`s name in both languages!", SystemMessage.ERROR));
            return "admin/vehicle-gear-form";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleGearShift = new VehicleGearShift(gearRu, gearEn);
        dao.save(vehicleGearShift);

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully created!", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-gear-list";
    }
}
