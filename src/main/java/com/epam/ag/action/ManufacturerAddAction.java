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
public class ManufacturerAddAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String manufacturer = req.getParameter("manufacturer-name");
        if (manufacturer.isEmpty()) {
            req.setAttribute("systemMessage", new SystemMessage("Please, enter manufacturer name!", SystemMessage.ERROR));
            return "admin/manufacturer-form";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer(manufacturer);
        dao.save(vehicleManufacturer);

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully created!", SystemMessage.SUCCESS));
        return "redirect:controller?action=manufacturers-list";
    }
}
