package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.utils.DateConverter;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Create order and setting dates to it
 *
 * @author Govorov Andrey.
 */
public class OrderSetDateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(OrderSetDateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("order.dates", req);
        if (systemMessage.hasErrors()) {
            log.trace("Validator fail");
            req.setAttribute("systemMessage", systemMessage);
            return "index";
        }

        String dateStartString = req.getParameter("date_start");
        String dateEndString = req.getParameter("date_end");

        Date dateStart = DateConverter.strToDate(dateStartString);
        Date dateEnd = DateConverter.strToDate(dateEndString);

        // Compare two dates. If first is greater than first one - error
        if (dateStart.getTime() > dateEnd.getTime()) {
            log.trace("First date greater than second: {} > {}", dateStartString, dateEndString);
            req.setAttribute("systemMessage", new SystemMessage("order.dates.wrong", SystemMessage.ERROR));
            return "index";
        }

        // Save order to session
        Order order = new Order();
        order.setStartDate(dateStart);
        order.setEndDate(dateEnd);

        req.getSession(true).setAttribute("order", order);
        return "redirect:controller?action=order-vehicle";
    }
}
