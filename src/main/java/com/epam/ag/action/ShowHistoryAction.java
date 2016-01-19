package com.epam.ag.action;

import com.epam.ag.model.Order;
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
public class ShowHistoryAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowHistoryAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!getUser(req)) {
            return "redirect:controller?action=login";
        }

        // Get all closed orders
        List statusList = Arrays.asList(
                Order.OrderStatus.CLOSED
        );

        Map<String, SqlParams> params = new HashMap<>();
        params.put("status", new SqlParams(statusList));

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getUsersOrderList(user, params);
        req.setAttribute("orders", orderList);

        return "client/order-list";
    }
}
