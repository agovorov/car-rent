package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.dict.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class VehicleCreateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VehicleCreateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String vehicleName = req.getParameter("vehicle-name");
        if (vehicleName.isEmpty()) {
            req.setAttribute("vehicleError", "Please, enter vehicle name!");
            return "admin/vehicle-form-add";
        }

        // It`s ok save it and redirect to list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        VehicleManufacturer manufacturer = new VehicleManufacturer(vehicleName);
        dao.save(manufacturer);

        // It`s ok
        return "redirect:admin/vehicle-list";
    }
}
