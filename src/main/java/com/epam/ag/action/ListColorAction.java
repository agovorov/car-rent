package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.dict.VehicleBodyColor;
import com.epam.ag.service.ColorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ListColorAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ColorService colorService = new ColorService();
        List<VehicleBodyColor> colors = colorService.getColorsList();
        req.setAttribute("colors", colors);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/color-list";
    }
}
