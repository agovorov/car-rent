package com.epam.ag.filter;

import com.epam.ag.utils.CookieManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Govorov Andrey
 */
public class LocaleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // Read from session
        HttpSession session = req.getSession(false);

        // Read from cookie
        String locale = null;
        Cookie cookie = CookieManager.findParam(req, "locale");
        if (cookie != null) {
            log.trace("Found locale in cookie");
            locale = cookie.getValue();
        }

        // If found locale in session or in cookie trying to set it
        if (session != null && locale != null) {
            log.trace("Set locale to: {}", locale);
            Locale userLocale = new java.util.Locale(locale);
            Config.set(session, Config.FMT_LOCALE, userLocale);
            req.setAttribute("locale", locale);
        }
        // Go next filter
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
