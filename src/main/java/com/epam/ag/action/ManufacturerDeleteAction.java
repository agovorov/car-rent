package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class ManufacturerDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long manufacturerId = Long.valueOf(req.getParameter("id"));
        if (manufacturerId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/manufacturers-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer = dao.getById(manufacturerId);

        if (vehicleManufacturer == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry. Record not found!", SystemMessage.ERROR));
            return "admin/manufacturers-list";
        }

        if (req.getMethod().equalsIgnoreCase("GET")) {
            // Show confirm page
            req.setAttribute("model", vehicleManufacturer);
            return "admin/manufacturer-delete";
        }

        // Do remove
        boolean isDeleted = dao.delete(vehicleManufacturer);
        if (!isDeleted) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, unable to delete.", SystemMessage.ERROR));
            return "admin/manufacturers-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully deleted!", SystemMessage.SUCCESS));
        return "redirect:controller?action=manufacturers-list";
    }
}