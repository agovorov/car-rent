package com.epam.ag.action;

import com.epam.ag.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Govorov Andrey
 */
public class UserAction {

    protected User user;

    /**
     * If user exist in session
     *
     * @param req
     * @return
     */
    protected boolean getUser(HttpServletRequest req) {
        user = (User) req.getSession(false).getAttribute("user");
        return user != null;
    }
}
