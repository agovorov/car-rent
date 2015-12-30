package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ShowLoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowLoginAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (AuthManager.isAuthorized(req)) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("auth.already.login", SystemMessage.ERROR));
            return "redirect:controller?action=cabinet";
        }

        // It`s ok - display login form
        return "login";
    }
}
