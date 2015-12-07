package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleGearShiftDao;
import com.epam.ag.model.dict.VehicleGearShift;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleGearDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long gearId = Long.valueOf(req.getParameter("id"));
        if (gearId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, wrong ID parameter!", SystemMessage.Type.ERROR));
            return "admin/vehicle-gear-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        VehicleGearShift vehicleGearShift = new VehicleGearShift();
        vehicleGearShift = dao.getById(gearId);

        if (vehicleGearShift == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry. Record not found!", SystemMessage.Type.ERROR));
            return "admin/vehicle-gear-list";
        }

        // Show confirm page
        if (req.getMethod().equalsIgnoreCase("GET")) {
            req.setAttribute("model", vehicleGearShift);
            return "admin/vehicle-gear-delete";
        }

        // Do remove
        boolean isDeleted = dao.delete(vehicleGearShift);
        if (!isDeleted) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, unable to delete.", SystemMessage.Type.ERROR));
            return "admin/vehicle-gear-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully deleted!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=vehicle-gear-list";
    }
}
