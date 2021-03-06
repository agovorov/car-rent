package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowRegisterPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (AuthManager.isAuthorized(req)) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("auth.already.registered", SystemMessage.ERROR));
            return "redirect:controller?action=cabinet";
        }

        // It`s ok - display register form
        return "register";
    }
}
