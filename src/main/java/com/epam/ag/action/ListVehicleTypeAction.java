package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ListVehicleTypeAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TypeService service = new TypeService();
        List<VehicleBodyType> types = service.getBodyTypeList();
        req.setAttribute("types", types);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/vehicle-type-list";
    }
}
