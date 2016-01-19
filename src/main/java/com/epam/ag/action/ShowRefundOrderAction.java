package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowRefundOrderAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowRefundOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long orderId;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.trace("Wrong order id: {}", req.getParameter("id"));
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        if (!getUser(req)) {
            return "redirect:controller?action=login";
        }

        OrderService orderService = new OrderService();
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        if (order.getCustomer().getId() != user.getId()) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.not.your.order", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        // Check status if status equals
        if (order.getStatus() != Order.OrderStatus.DAMAGED) {
            log.trace("Wrong order status: {}", order);
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.status", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        // Loading all information about order
        orderService.loadVehicleData(order);
        orderService.loadCustomerData(order);

        req.setAttribute("order", order);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "client/order-refund";
    }
}
