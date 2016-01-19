package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.utils.DictionaryLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Display form for adding vehicle
 *
 * @author Govorov Andrey
 */
public class AddVehicleFormAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DictionaryLoader.loadDictionaries(req);
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        return "admin/vehicle-form";
    }
}
