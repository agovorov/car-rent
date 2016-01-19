package com.epam.ag.action.helpers;

import com.epam.ag.model.User;
import com.epam.ag.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Util class for login/logout form
 *
 * @author Govorov Andrey
 */
public class AuthManager {

    private static final Logger log = LoggerFactory.getLogger(AuthManager.class);

    /**
     * Comparing passwords
     *
     * @param user
     * @param plainPassword
     * @return
     */
    public static boolean authenticate(User user, String plainPassword) {
        if (!PasswordUtil.validatePassword(plainPassword, user.getPassword())) {
            log.info("Wrong password for email {}", user.getEmail());
            return false;
        }
        return true;
    }

    /**
     * Load user`s info in session
     *
     * @param user
     * @param req  Htt[ServiceRequest
     */
    public static void authorize(User user, HttpServletRequest req) {
        // Save to session
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);

        // TODO Need to keep locale in database and read it here
    }

    /**
     * Check if user is logged in
     *
     * @param req
     * @return
     */
    public static boolean isAuthorized(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return !(session == null || session.getAttribute("user") == null);
    }


    /**
     * Remove data from session
     *
     * @param req
     */
    public static void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
    }
}
