package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Remove session
        AuthManager.logout(req);

        return "redirect:";
    }
}
