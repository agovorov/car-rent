package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.model.dict.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleListAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowVehicleListAction.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Display vehicle page with list of cars
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        List<Vehicle> vehicles = dao.getAll();
        req.setAttribute("vehicles", vehicles);
        daoFactory.close();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        return "admin/vehicle-list";
    }
}