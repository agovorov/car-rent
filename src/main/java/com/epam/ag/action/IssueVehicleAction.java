package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class IssueVehicleAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(IssueVehicleAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long orderId;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong id parameter", e);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=order-payed";
        }

        // Load order model
        log.trace("Looking for an order #{}", orderId);
        OrderService service = new OrderService();
        Order order = service.getOrderById(orderId);
        if (order == null) {
            log.trace("Order not found");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=order-payed";
        }

        // Loading all information about order
        service.loadVehicleData(order);
        service.loadCustomerData(order);

        // Order is not payed
        if (!order.getStatus().equals(Order.OrderStatus.PAYED)) {
            log.trace("Order has wrong status");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.order", SystemMessage.ERROR));
            return "admin/order-vehicle-issue";
        }

        log.trace("Param: {}", req.getParameter("issued"));
        if (req.getParameter("issued") == null) {
            log.trace("Form issue param: false");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.not.issued.checkbox", SystemMessage.ERROR));
            return "admin/order-vehicle-issue";
        }

        log.trace("Form issue param: true");

        // Change status
        order.setStatus(Order.OrderStatus.VEHICLE_TAKEN);
        log.trace("Going to save order: {}", order);
        boolean isSaved = service.saveOrder(order);
        if (!isSaved) {
            log.trace("Unable to update order information");
            req.setAttribute("systemMessage", new SystemMessage("order.confirm.detail.save.fail", SystemMessage.ERROR));
            return "admin/order-vehicle-issue";
        }

        // TODO Send email to user with status

        log.trace("Order successfully confirmed: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.issued.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=cabinet";
    }
}
