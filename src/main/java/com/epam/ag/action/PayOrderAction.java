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
 * User pay money for the vehicle
 *
 * @author Govorov Andrey.
 */
public class PayOrderAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(PayOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Validate form
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("order.pay", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "client/order-pay";
        }

        if (!getUser(req)) {
            return "redirect:controller?action=login";
        }

        Long orderId = null;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong order  id: {}", orderId);
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

        long orderOwnerId = order.getCustomer().getId();
        long currentUserId = user.getId();
        if (orderOwnerId != currentUserId) {
            log.trace("Wrong order owner. Is not current user.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.not.your.order", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        // Change status
        // TODO I`m not going to save transaction info now. But surely should...
        order.setStatus(Order.OrderStatus.PAYED);

        OrderService orderService = new OrderService();
        boolean isSaved = orderService.saveOrder(order);
        if (!isSaved) {
            log.trace("Unable to update order`s status");
            req.setAttribute("systemMessage", new SystemMessage("order.pay.fail", SystemMessage.ERROR));
            return "client/order-pay";
        }

        log.trace("Order successfully confirmed: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.confirmed.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=orders";
    }
}
