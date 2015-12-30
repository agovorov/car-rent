package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.Vehicle;
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
public class SelectVehicleAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(SelectVehicleAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Order order = null;
        try {
            order = (Order) req.getSession(false).getAttribute("order");
        } catch (NullPointerException e) {
            log.trace("No order in session. Go back to date select.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.select.vehicle.no.dates", SystemMessage.WARNING));
            return "redirect:controller?action=order";
        }

        // Get available cars
        OrderService service = new OrderService();
        List<Vehicle> availableVehicles = service.getAvailableVehicles(
                order.getStartDate(),
                order.getEndDate()
        );


        // Show cars
        log.trace("Ok, showing available cars");
        req.setAttribute("order", order);
        req.setAttribute("vehicles", availableVehicles);
        return "client/order-vehicle";
    }
}
