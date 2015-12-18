package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.User;
import com.epam.ag.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class ListUserAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = new UserService();
        List<User> users = service.getUsersList();
        req.setAttribute("users", users);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/user-list";
    }
}
