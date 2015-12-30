package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.model.User;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("login", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "login";
        }

        String email = req.getParameter("login");
        String password = req.getParameter("passwd");

        // Searching user
        UserService service = new UserService();
        User user = service.getUserByEmail(email);
        if (user == null) {
            log.info("User with email {} is not found", email);
            req.setAttribute("systemMessage", new SystemMessage("auth.wrong.user", SystemMessage.ERROR));
            return "login";
        }

        // Try to authenticate
        boolean isValid = AuthManager.authenticate(user, password);
        if (!isValid) {
            log.trace("Wrong password for user {}", user.getEmail());
            req.setAttribute("systemMessage", new SystemMessage("auth.wrong.password", SystemMessage.ERROR));
            return "login";
        }

        AuthManager.authorize(user, req);

        // Back to return url
        String returnUrl = sendUserBack(req, resp);
        if (returnUrl != null) {
            return "redirect:controller?action=" + returnUrl;
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("auth.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=cabinet";
    }

    /**
     * Trying to send user back
     * @param req
     * @param resp
     */
    private String sendUserBack(HttpServletRequest req, HttpServletResponse resp) {
        String returnUrl = (String) req.getSession(false).getAttribute("returnUrl");
        log.trace("Going to send user to... here `{}`", returnUrl);
        return returnUrl;
    }
}
