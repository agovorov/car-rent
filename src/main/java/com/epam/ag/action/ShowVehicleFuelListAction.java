package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleFuelTypeDao;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.model.dict.VehicleFuelType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleFuelListAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Read color list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleFuelTypeDao dao = daoFactory.getDao(VehicleFuelTypeDao.class);
        List<VehicleFuelType> fuels = dao.getAll();
        daoFactory.close();
        req.setAttribute("fuels", fuels);
        return "admin/vehicle-fuel-list";
    }
}
