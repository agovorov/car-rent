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
public class ManufacturerUpdateAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long manufacturerId = Long.valueOf(req.getParameter("id"));

        if (manufacturerId <= 0) {
            req.setAttribute("errorMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/manufacturers-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer = dao.getById(manufacturerId);

        if (vehicleManufacturer == null) {
            req.setAttribute("errorMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            daoFactory.close();
            return "admin/manufacturers-list";
        }

        if (req.getMethod().equals("POST")) {
            String manufacturer = req.getParameter("manufacturer-name");
            if (manufacturer.isEmpty()) {
                req.setAttribute("errorMessage", new SystemMessage("Please, enter manufacturer name!", SystemMessage.ERROR));
                daoFactory.close();
                return "admin/manufacturer-form";
            }

            // Save
            vehicleManufacturer.setValues(manufacturer, null);
            dao.save(vehicleManufacturer);
            req.setAttribute("errorMessage", "Record successfully saved.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            daoFactory.close();
            return "redirect:controller?action=manufacturer-update&id=" +vehicleManufacturer.getId();
        }

        req.setAttribute("vehicleManufacturer", vehicleManufacturer);
        daoFactory.close();
        return "admin/manufacturer-form";
    }
}
