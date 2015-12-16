package com.epam.ag.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class CookieManager {

    private static final Logger log = LoggerFactory.getLogger(CookieManager.class);

    public static Cookie findParam(HttpServletRequest req, String parameterName) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (parameterName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static void setParam(HttpServletResponse response, String parameterName, String parameterValue) {
        Cookie newCookie = new Cookie(parameterName, parameterValue);
        response.addCookie(newCookie);
    }
}
