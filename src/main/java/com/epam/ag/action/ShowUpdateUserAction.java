package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.RoleService;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class ShowUpdateUserAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId;
        try {
            userId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=user-list";
        }

        // Get data for showing record
        UserService service = new UserService();
        User user = service.getUserById(userId);
        if (user == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=user-list";
        }

        RoleService serviceRoles = new RoleService();
        List<UserRole> roles = serviceRoles.getRolesList();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());

        req.setAttribute("breadcrumbItems", breadcrumbItems);
        req.setAttribute("roles", roles);
        req.setAttribute("user", user);
        return "admin/user-form";
    }
}
