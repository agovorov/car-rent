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
public class VehicleTypeDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long typeId = Long.valueOf(req.getParameter("id"));
        if (typeId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, wrong ID parameter!", SystemMessage.Type.ERROR));
            return "admin/vehicle-type-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        VehicleBodyType vehicleBodyType = new VehicleBodyType();
        vehicleBodyType = dao.getById(typeId);

        if (vehicleBodyType == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry. Record not found!", SystemMessage.Type.ERROR));
            return "admin/vehicle-type-list";
        }

        // Show confirm page
        if (req.getMethod().equalsIgnoreCase("GET")) {
            req.setAttribute("model", vehicleBodyType);
            return "admin/vehicle-type-delete";
        }

        // Do remove
        boolean isDeleted = dao.delete(vehicleBodyType);
        if (!isDeleted) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, unable to delete.", SystemMessage.Type.ERROR));
            return "admin/vehicle-type-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully deleted!", SystemMessage.Type.SUCCESS));
        return "redirect:controller?action=vehicle-type-list";
    }
}
