package com.epam.ag.action;

import com.epam.ag.utils.CookieManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change user`s locale
 *
 * @author Govorov Andrey
 */
public class LocaleChangeAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LocaleChangeAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String redirectPath = "";

        String currentQueryString = req.getHeader("referer");

        // Get URL after `controller` to redirect later
        int urlSplitIdx = currentQueryString.indexOf("/controller");
        if (urlSplitIdx != -1) {
            redirectPath = currentQueryString.substring(urlSplitIdx + 1);
        }

        String localeStr = req.getParameter("lang");
        log.trace("Switch locale to {}", localeStr);

        // Save to session
        req.getSession(true).setAttribute("locale", localeStr);

        // Save to cookie
        CookieManager.setParam(resp, "locale", localeStr);
        return "redirect:" + redirectPath;
    }
}
