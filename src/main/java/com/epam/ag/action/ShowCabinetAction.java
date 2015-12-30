package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowCabinetAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!AuthManager.isAuthorized(req)) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("auth.not.registered", SystemMessage.ERROR));
            return "redirect:controller?action=login";
        }

        // User role
        User user = (User) req.getSession(false).getAttribute("user");

        if (user.getRole().equals(UserRole.ADMIN)) {
            return "admin/cabinet";
        }

        // Show client`s page
        return "client/cabinet";
    }
}
