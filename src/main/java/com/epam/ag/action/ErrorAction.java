package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ErrorAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "error-page";
    }
}
