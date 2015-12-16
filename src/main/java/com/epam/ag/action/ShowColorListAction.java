package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.model.dict.VehicleBodyColor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowColorListAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Read color list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleBodyColorDao dao = daoFactory.getDao(VehicleBodyColorDao.class);
        List<VehicleBodyColor> colors = dao.getAll();
        daoFactory.close();
        req.setAttribute("colors", colors);
        return "admin/color-list";
    }
}
