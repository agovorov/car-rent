package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class AddUserFormAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(AddUserFormAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        RoleService service = new RoleService();
        List<UserRole> roles = service.getRolesList();
        log.trace("Roles: {}", roles);
        req.setAttribute("roles", roles);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/user-form";
    }
}
