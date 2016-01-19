package com.epam.ag.action;

import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Display cabinet page
 *
 * @author Govorov Andrey
 */
public class ShowCabinetAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // User role
        User user = (User) req.getSession(false).getAttribute("user");

        if (user.getRole().equals(UserRole.ADMIN)) {
            return "admin/cabinet";
        }

        // Show client`s page
        return "client/cabinet";
    }
}
