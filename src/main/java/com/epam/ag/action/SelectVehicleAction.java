package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.ManufacturerService;
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
        Order order = (Order) req.getSession(false).getAttribute("order");
        if (order == null) {
            log.trace("No order in session. Go back to date select.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.select.vehicle.no.dates", SystemMessage.WARNING));
            return "redirect:";
        }

        // Get available cars
        OrderService service = new OrderService();
        List<Vehicle> availableVehicles = service.getAvailableVehicles(
                order.getStartDate(),
                order.getEndDate()
        );

        // Get list of manufacturers
        ManufacturerService manufacturerService = new ManufacturerService();
        List<VehicleManufacturer> manufacturers = manufacturerService.getManufacturerList();

        // Show cars
        log.trace("Ok, showing available cars");
        req.setAttribute("order", order);
        req.setAttribute("vehicles", availableVehicles);
        req.setAttribute("manufacturers", manufacturers);
        return "client/order-vehicle";
    }
}
