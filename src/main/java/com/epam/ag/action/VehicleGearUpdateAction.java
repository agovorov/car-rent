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
public class VehicleGearUpdateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleGearId = Long.valueOf(req.getParameter("id"));

        if (vehicleGearId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/vehicle-gear-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleGearShift = new VehicleGearShift();
        vehicleGearShift = dao.getById(vehicleGearId);

        if (vehicleGearShift == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            return "admin/vehicle-gear-list";
        }

        if (req.getMethod().equals("POST")) {
            String gearRu = req.getParameter("gear-name-ru");
            String gearEn = req.getParameter("gear-name-en");
            if (gearRu.isEmpty() || gearEn.isEmpty()) {
                req.setAttribute("vehicleGearShift", vehicleGearShift);
                req.setAttribute("systemMessage", new SystemMessage("Please, enter vehicle`s type name in both languages!", SystemMessage.ERROR));
                return "admin/vehicle-gear-form";
            }

            // Save
            vehicleGearShift.setValues(gearRu, gearEn);
            dao.save(vehicleGearShift);
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-gear-update&id=" +vehicleGearShift.getId();
        }

        req.setAttribute("vehicleGearShift", vehicleGearShift);
        return "admin/vehicle-gear-form";
    }
}
