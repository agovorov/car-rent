package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.user.Address;
import com.epam.ag.model.user.Passport;
import com.epam.ag.service.OrderService;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.DateConverter;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Govorov Andrey
 */
public class VerifyOrderAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VerifyOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("confirm", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "client/order-verify";
        }

        Order order = (Order) req.getSession(false).getAttribute("order");
        if (order == null) {
            log.trace("Order is not found in session.");
            req.setAttribute("systemMessage", new SystemMessage("order.not.found", SystemMessage.ERROR));
            return "client/date_select";
        }


        // Set status to WAITING confirmation
        order.setStatus(Order.OrderStatus.WAITING);
        order.setDateOfOrder(new Date());

        log.trace("Going to save order: {}", order);
        OrderService orderService = new OrderService();
        boolean isSaved = orderService.saveOrder(order);
        if (!isSaved) {
            log.error("Unable to save order via OrderService");
            req.setAttribute("systemMessage", new SystemMessage("order.confirm.save.fail", SystemMessage.ERROR));
            return "client/date_select";
        }

        // It`s ok
        log.trace("Order successfully created: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirm.success", SystemMessage.SUCCESS));
        return "redirect:";
    }
}