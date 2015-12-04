package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class IndexAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "index";
    }
}
