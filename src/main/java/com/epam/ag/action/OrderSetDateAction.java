package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.utils.DateConverter;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Govorov Andrey.
 */
public class OrderSetDateAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("order.dates", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "client/date_select";
        }

        String dateStartString = req.getParameter("date_start");
        String dateEndString = req.getParameter("date_end");

        Date dateStart = DateConverter.strToDate(dateStartString);
        Date dateEnd = DateConverter.strToDate(dateEndString);

        // Save order to session
        Order order = new Order();
        order.setStartDate(dateStart);
        order.setEndDate(dateEnd);

        req.getSession(true).setAttribute("order", order);
        return "redirect:controller?action=order-vehicle";
    }
}
