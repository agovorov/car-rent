package com.epam.ag.action.helpers;

import com.epam.ag.model.User;
import com.epam.ag.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Govorov Andrey
 */
public class AuthManager {

    private static final Logger log = LoggerFactory.getLogger(AuthManager.class);

    public static boolean authenticate(User user, String plainPassword) {
        // Comparing passwords
        String hashedPassword = PasswordUtil.hashPassword(plainPassword);
        if (!hashedPassword.equals(user.getPassword())) {
            log.info("Wrong password for email {}", user.getEmail());
            return false;
        }
        return true;
    }

    public static void authorize(User user, HttpServletRequest req) {
        // Save to session
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);

        // TODO Need to keep locale in database
//        userLocaleName = (String) session.getAttribute("locale");
    }

    public static boolean isAuthorized(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return !(session == null || session.getAttribute("user") == null);
    }


    public static void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
    }
}
