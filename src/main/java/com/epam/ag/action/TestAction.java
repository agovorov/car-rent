package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class TestAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderService service = new OrderService();
        Order order = service.getOrderById(15L);
        service.loadCustomerData(order);
        service.loadVehicleData(order);
        req.setAttribute("order", order);
        return "client/order-verify";
    }
}
