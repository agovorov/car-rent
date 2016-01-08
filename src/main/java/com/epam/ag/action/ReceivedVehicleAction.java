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
 * @author Govorov Andrey.
 */
public class ReceivedVehicleAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ReceivedVehicleAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long orderId;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong order id parameter", e);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=order-list";
        }

        // Load order model
        log.trace("Looking for an order #{}", orderId);
        OrderService service = new OrderService();
        Order order = service.getOrderById(orderId);
        if (order == null) {
            log.trace("Order not found");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=order-list";
        }

        // Order is not payed
        if (!order.getStatus().equals(Order.OrderStatus.VEHICLE_TAKEN)) {
            log.trace("Order has wrong status");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.order", SystemMessage.ERROR));
            return "redirect:controller?action=order-list";
        }

        // Loading all information about order
        service.loadVehicleData(order);
        service.loadCustomerData(order);
        req.setAttribute("order", order);

        // No damage found - close order
        log.trace("Damage param: {}", req.getParameter("damage_found"));
        if (req.getParameter("damage_found") == null) {
            log.trace("No damage was found. Close order #{}", order.getId());
            order.setStatus(Order.OrderStatus.CLOSED);
        } else {
            // if damage found then set status DAMAGED and waiting payment
            // Validate form
            FormValidator validator = new FormValidator();
            SystemMessage systemMessage = validator.validateForm("return.vehicle", req);
            if (systemMessage.hasErrors()) {
                log.trace("Validator: {}", systemMessage.getErrors());
                req.setAttribute("systemMessage", systemMessage);
                return "admin/order-vehicle-receive";
            }
            log.trace("Vehicle was damaged.");

            // Add status and damage note
            order.setDamageNote(req.getParameter("damage_note"));
            order.setDamagePrice(
                    Integer.parseInt(req.getParameter("damage_cost"))
            );

            order.setStatus(Order.OrderStatus.DAMAGED);
        }

        log.trace("Going to save order: {}", order);
        boolean isSaved = service.saveOrder(order);
        if (!isSaved) {
            log.trace("Unable to update order information");
            req.setAttribute("systemMessage", new SystemMessage("order.confirm.detail.save.fail", SystemMessage.ERROR));
            return "admin/order-vehicle-receive";
        }

        // TODO Send email to user with status
        log.trace("Order successfully confirmed: {}", order);
        req.getSession().setAttribute("systemMessage", new SystemMessage("order.issued.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=order-list";
    }
}