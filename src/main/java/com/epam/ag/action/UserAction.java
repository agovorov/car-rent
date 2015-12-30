package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Govorov Andrey
 */
public class UserAction {

    protected User user;

    // TODO this is functionality for other class. Fail with first principle
    protected boolean checkUser(HttpServletRequest req, UserRole role) {
        if (!AuthManager.isAuthorized(req)) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("auth.not.registered", SystemMessage.ERROR));
            return false;
        }

        user = (User) req.getSession(false).getAttribute("user");

        if (user == null) {
            return false;
        }

        return user.getRole().equals(role);

    }
}
