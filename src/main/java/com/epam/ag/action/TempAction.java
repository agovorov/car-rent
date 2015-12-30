package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.model.User;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class TempAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(TempAction.class);
    private String role;

    public TempAction(String role) {
        this.role = role;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String email;

        if (role.equals("user")) {
            log.trace("User login");
            email = "thegovorovs@gmail.com";
        } else {
            log.trace("Admin login");
            email = "admin@admin.ru";
        }

        UserService us = new UserService();
        User user = us.getUserByEmail(email);

        if (user == null) {
            log.info("User with email {} is not found", email);
            req.setAttribute("systemMessage", new SystemMessage("auth.wrong.user", SystemMessage.ERROR));
            return "login";
        }

        AuthManager.authorize(user, req);

        req.getSession(true).setAttribute("user", user);
        return "redirect:controller?action=cabinet";
    }
}
