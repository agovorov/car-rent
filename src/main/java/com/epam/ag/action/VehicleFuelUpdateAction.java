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
public class VehicleFuelUpdateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleFuelId = Long.valueOf(req.getParameter("id"));

        if (vehicleFuelId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/vehicle-fuel-list";
        }

        // Loading model
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        VehicleFuelType vehicleFuelType = new VehicleFuelType();
        vehicleFuelType = dao.getById(vehicleFuelId);

        if (vehicleFuelType == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            return "admin/vehicle-fuel-list";
        }

        if (req.getMethod().equals("POST")) {
            String fuelRu = req.getParameter("fuel-name-ru");
            String fuelEn = req.getParameter("fuel-name-en");
            if (fuelRu.isEmpty() || fuelEn.isEmpty()) {
                req.setAttribute("vehicleFuelType", vehicleFuelType);
                req.setAttribute("systemMessage", new SystemMessage("Please, enter vehicle`s type name in both languages!", SystemMessage.ERROR));
                return "admin/vehicle-fuel-form";
            }

            // Save
            vehicleFuelType.setValues(fuelRu, fuelEn);
            dao.save(vehicleFuelType);
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-fuel-update&id=" + vehicleFuelType.getId();
        }

        req.setAttribute("vehicleFuelType", vehicleFuelType);
        return "admin/vehicle-fuel-form";
    }
}
