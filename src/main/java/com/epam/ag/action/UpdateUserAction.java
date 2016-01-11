package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.RoleService;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class UpdateUserAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId;
        try {
            userId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=user-list";
        }

        RoleService serviceRoles = new RoleService();
        List<UserRole> roles = serviceRoles.getRolesList();
        req.setAttribute("roles", roles);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);


        // Get data for showing record
        UserService service = new UserService();
        User user = service.getUserById(userId);
        if (user == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=user-list";
        }

        //Validation
        FormValidator validator = new FormValidator();
        validator.ignoreRules(Arrays.asList(
                "user.email.2"
        ));
        SystemMessage systemMessage = validator.validateForm("user", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/user-form";
        }

        // Prepare model
        Long userRoleId = Long.parseLong(req.getParameter("role"));
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setPhone(req.getParameter("phone"));
        user.setEmail(req.getParameter("email"));
        user.setRole(new UserRole(userRoleId));

        boolean isSaved = service.saveUser(user);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("user.save.fail", SystemMessage.ERROR));
            return "admin/user-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("user.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=user-update&id=" + user.getId();
    }
}
