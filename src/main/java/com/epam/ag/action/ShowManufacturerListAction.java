package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.dict.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by Govorov Andrey.
 */
public class ShowManufacturerListAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowManufacturerListAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Read manufacturer list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        List<VehicleManufacturer> manufacturers = dao.getAll();

        req.setAttribute("manufacturerList", manufacturers);
        return "admin/manufacturers-list";
    }
}
