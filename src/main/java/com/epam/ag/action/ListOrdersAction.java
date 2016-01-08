package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SqlParams;
import com.epam.ag.utils.SystemMessage;
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
public class ListOrdersAction implements Action {

    private String param;
    private static final Logger log = LoggerFactory.getLogger(ListOrdersAction.class);


    public ListOrdersAction(String param) {
        this.param = param;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String,SqlParams> params = new HashMap<>();
        if (param != null) {
            params.put("status", new SqlParams(param));
        }

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersList(params);
        req.setAttribute("orders", orderList);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "admin/order-list";
    }
}
