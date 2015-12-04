package com.epam.ag.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class RegisterAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

//        if (!Validator.isValidLogin(login)) {
//
//        }
//
//        User user = new user();
//        dao.save...

        return "redirect:success-reg";
    }
}
