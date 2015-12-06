package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Govorov Andrey.
 */
public class ShowPageAction implements Action {

    private String page;

    public ShowPageAction(String page) {
        this.page = page;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return page;
    }
}
