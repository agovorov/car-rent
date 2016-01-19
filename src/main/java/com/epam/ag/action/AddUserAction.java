package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.RoleService;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.PasswordUtil;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class than added user to system
 *
 * @author Govorov Andrey.
 */
public class AddUserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(AddUserAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        RoleService serviceRoles = new RoleService();
        List<UserRole> roles = serviceRoles.getRolesList();
        req.setAttribute("roles", roles);
        log.trace("Roles2: {}", roles);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("user", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/user-form";
        }

        // It`s ok save it and redirect to list
        Long userRoleId = Long.parseLong(req.getParameter("role"));
        User user = new User();
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setPhone(req.getParameter("phone"));
        user.setEmail(req.getParameter("email"));
        user.setRole(new UserRole(userRoleId));

        // Generate random password
        String password = PasswordUtil.generateHashedRandomPassword();
        user.setPassword(password);

        UserService service = new UserService();
        boolean isAdded = service.saveUser(user);
        if (!isAdded) {
            log.error("Unable to save user: {}", user);
            req.setAttribute("systemMessage", new SystemMessage("user.save.fail", SystemMessage.ERROR));
            return "admin/user-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("user.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=user-list";
    }
}
