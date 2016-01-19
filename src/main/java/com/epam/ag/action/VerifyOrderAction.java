package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;
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
        // Looking for an order
        Order order = (Order) req.getSession(false).getAttribute("order");
        if (order == null) {
            log.trace("Order is not found in session.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.not.found", SystemMessage.ERROR));
            return "redirect:";
        }

        // Check if passport data is set
        User user = (User) req.getSession(false).getAttribute("user");
        if (user == null) {
            log.trace("User is not found in session.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.not.found", SystemMessage.ERROR));
            return "redirect:";
        }

        if (user.getPassport() == null) {
            log.trace("User has no passport data.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.user.no.passport.data", SystemMessage.ERROR));
            return "redirect:";
        }

        // Set status to WAITING confirmation
        order.setStatus(Order.OrderStatus.WAITING);
        order.setDateOfOrder(new Date());

        log.trace("Going to save order: {}", order);
        OrderService orderService = new OrderService();
        boolean isSaved = orderService.saveOrder(order);
        if (!isSaved) {
            log.error("Unable to save order via OrderService");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirm.save.fail", SystemMessage.ERROR));
            return "redirect:";
        }

        // It`s ok
        log.trace("Order successfully created: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirm.success", SystemMessage.SUCCESS));
        return "redirect:";
    }
}