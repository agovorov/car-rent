package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Order;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.OrderService;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class ShowOrderDetailAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowOrderDetailAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long orderId;
        try {
            orderId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        if (!checkUser(req, UserRole.CLIENT)) {
            /// return "redirect:controller?action=login";
            log.warn("WRONG user role");
        }

        // Load order model
        OrderService service = new OrderService();
        Order order = service.getOrderById(orderId);
        if (order == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        service.loadCustomerData(order);
        if (order.getCustomer().getId() != user.getId()) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.form.not.owner", SystemMessage.ERROR));
            return "redirect:controller?action=orders";
        }

        // Loading all information about order
        service.loadVehicleData(order);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        req.setAttribute("order", order);
        return "client/order-detail";
    }
}
