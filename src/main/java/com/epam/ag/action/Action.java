package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public interface Action {
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
