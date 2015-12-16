package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.dict.VehicleBodyType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleTypeListAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Read color list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        List<VehicleBodyType> types = dao.getAll();
        daoFactory.close();
        req.setAttribute("types", types);
        return "admin/vehicle-type-list";
    }
}
