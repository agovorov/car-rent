package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.user.UserRole;
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
public class RefundOrderAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RefundOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkUser(req, UserRole.CLIENT)) {
            log.warn("WRONG user role");
            /// return "redirect:controller?action=login";
        }

        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("order.pay", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "client/order-refund";
        }

        Long orderId = null;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong order id: {}. Enter valid ID", orderId);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        // Load order model
        OrderService service = new OrderService();
        Order order = service.getOrderById(orderId);
        if (order == null) {
            log.trace("Order not found #Id{}", orderId);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=order-confirm-detail";
        }

        // Check status if status equals
        if (order.getStatus() != Order.OrderStatus.DAMAGED) {
            log.trace("Wrong order status: {}", order);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.status", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        long orderOwnerId = order.getCustomer().getId();
        long currentUserId = user.getId();
        if (orderOwnerId != currentUserId) {
            log.trace("Wrong order owner. Is not current user.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.not.your.order", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        order.setStatus(Order.OrderStatus.CLOSED);

        OrderService orderService = new OrderService();
        boolean isSaved = orderService.saveOrder(order);
        if (!isSaved) {
            log.trace("Unable to update order`s status");
            req.setAttribute("systemMessage", new SystemMessage("order.pay.fail", SystemMessage.ERROR));
            return "client/order-refund";
        }

        log.trace("Order successfully confirmed: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirmed.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=orders";
    }
}
