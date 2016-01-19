package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SqlParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public class ShowOrdersAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowOrdersAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!getUser(req)) {
            return "redirect:controller?action=login";
        }

        // Get all non closed orders
        List statusList = Arrays.asList(
                Order.OrderStatus.WAITING,
                Order.OrderStatus.CONFIRMED,
                Order.OrderStatus.PAYED,
                Order.OrderStatus.VEHICLE_TAKEN,
                Order.OrderStatus.VEHICLE_RECEIVED,
                Order.OrderStatus.DENIED,
                Order.OrderStatus.DAMAGED
        );

        Map<String, SqlParams> params = new HashMap<>();
        params.put("status", new SqlParams(statusList));

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getUsersOrderList(user, params);
        req.setAttribute("orders", orderList);
//        log.trace("Order: {}", orderList);
//        log.trace("user: {}", user);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "client/order-list";
    }
}
