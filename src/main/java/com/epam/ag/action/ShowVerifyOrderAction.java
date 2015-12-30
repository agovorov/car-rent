package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.Vehicle;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class ShowVerifyOrderAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowVerifyOrderAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = null;
        Order order = null;
        long vehicleId;
        try {
            user = (User) req.getSession(false).getAttribute("user");
            order = (Order) req.getSession(false).getAttribute("order");
            vehicleId = Long.parseLong(req.getParameter("id"));
        } catch (NullPointerException e) {
            log.trace("No order in session. Go back to date select.");
            req.getSession(true).setAttribute("systemMessage", new SystemMessage("order.select.vehicle.no.dates", SystemMessage.WARNING));
            return "redirect:controller?action=order";
        }

        // User is not logged in
        if (user == null) {
            // Remember url
            String currentQueryString = req.getQueryString();
            currentQueryString = currentQueryString.replace("action=", "");
            log.trace("Return URL after login: {}", currentQueryString);
            req.getSession(true).setAttribute("returnUrl", currentQueryString);
            req.getSession(true).setAttribute("systemMessage", new SystemMessage("order.login.please", SystemMessage.ERROR));
            return "redirect:controller?action=login";
        }

        // Is car exists?
        VehicleService vehicleService = new VehicleService();
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            req.getSession(true).setAttribute("systemMessage", new SystemMessage("order.wrong.car.id", SystemMessage.ERROR));
            return "redirect:controller?action=order";
        }

        order.setVehicle(vehicle);
        order.setCustomer(user);

        // Show cars
        log.trace("Ok, showing available cars");
        req.setAttribute("order", order);
        return "client/order-verify";
    }
}
