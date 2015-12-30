package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey.
 */
public class ShowConfirmOrderAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowConfirmOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String,Object> params = new HashMap<>();
        params.put("status", Order.OrderStatus.WAITING.name());

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersList(params);
        req.setAttribute("orders", orderList);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/order-confirm";
    }
}
