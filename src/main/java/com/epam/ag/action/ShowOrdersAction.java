package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowOrdersAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowOrdersAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkUser(req, UserRole.CLIENT)) {
            /// return "redirect:controller?action=login";
            log.warn("WRONG user role");
        }

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getUsersOrderList(user);
        req.setAttribute("orders", orderList);
        log.trace("Order: {}", orderList);
        log.trace("user: {}", user);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "client/order-list";
    }
}
