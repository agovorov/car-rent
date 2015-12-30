package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class ConfirmOrderDetailAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ConfirmOrderDetailAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("confirm.detail", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/order-confirm-detail";
        }

        Long orderId;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong id parameter", e);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=order-confirm-detail";
        }

        // Load order model
        log.trace("Looking for an order #{}", orderId);
        OrderService service = new OrderService();
        Order order = service.getOrderById(orderId);
        if (order == null) {
            log.trace("Order not found");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=order-confirm-detail";
        }
        log.trace("Order founded.");

        // Which status for order
        String orderAction = req.getParameter("btn-confirm");
        String causeOfFailure = req.getParameter("reason");
        if (orderAction.equals("accept")) {
            // Change status to CONFIRMED
            log.trace("Accepted");
            order.setStatus(Order.OrderStatus.CONFIRMED);
        } else {
            // Change status to denied
            if (causeOfFailure.isEmpty()) {
                log.trace("Reason is not specified!");
                req.setAttribute("systemMessage", new SystemMessage("order.confirm.detail.empty.reason", SystemMessage.ERROR));
                return "admin/order-confirm-detail";
            }

            log.trace("Denied");
            order.setStatus(Order.OrderStatus.DENIED);
            order.setCauseOfFailure(causeOfFailure);
        }

        // Save it
        log.trace("Going to save order: {}", order);
        boolean isSaved = service.saveOrder(order);
        if (!isSaved) {
            log.trace("Unable to update order information");
            req.setAttribute("systemMessage", new SystemMessage("order.confirm.detail.save.fail", SystemMessage.ERROR));
            return "admin/order-confirm-detail";
        }

        // TODO Send email to user with status

        log.trace("Order successfully confirmed: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirmed.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=cabinet";
    }
}
