package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleFuelTypeDao;
import com.epam.ag.model.dict.VehicleFuelType;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleFuelDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long typeId = Long.valueOf(req.getParameter("id"));
        if (typeId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, wrong ID parameter!", SystemMessage.Type.ERROR));
            return "admin/vehicle-fuel-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = new VehicleFuelType();
        vehicleFuelType = dao.getById(typeId);

        if (vehicleFuelType == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry. Record not found!", SystemMessage.Type.ERROR));
            return "admin/vehicle-fuel-list";
        }

        // Show confirm page
        if (req.getMethod().equalsIgnoreCase("GET")) {
            req.setAttribute("model", vehicleFuelType);
            return "admin/vehicle-fuel-delete";
        }

        // Do remove
        boolean isDeleted = dao.delete(vehicleFuelType);
        if (!isDeleted) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, unable to delete.", SystemMessage.Type.ERROR));
            return "admin/vehicle-fuel-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully deleted!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=vehicle-fuel-list";
    }
}
