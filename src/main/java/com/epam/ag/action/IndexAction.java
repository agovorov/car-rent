package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Show index page
 *
 * @author Govorov Andrey.
 */
public class IndexAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "index";
    }
}
