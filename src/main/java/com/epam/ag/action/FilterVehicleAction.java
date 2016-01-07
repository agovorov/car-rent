package com.epam.ag.action;

import com.epam.ag.model.Order;
import com.epam.ag.model.Vehicle;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.utils.ajax.AjaxReturnFactory;
import com.epam.ag.utils.ajax.JsonAjaxReturn;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class FilterVehicleAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(FilterVehicleAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Order order = (Order) req.getSession(false).getAttribute("order");
        if (order == null) {
            log.trace("No order in session. Go back to date select.");
            req.getSession().setAttribute("systemMessage", new SystemMessage("order.select.vehicle.no.dates", SystemMessage.WARNING));
            return "redirect:";
        }

        String jsonFilter = req.getParameter("filter");
        Gson gson = new Gson();
        VehicleFilter filter = gson.fromJson(jsonFilter, VehicleFilter.class);
//
//        log.trace("Min price: {}", filter.minPrice);
//        log.trace("Max price: {}", filter.maxPrice);
//        log.trace("Manufacturer price: {}", filter.manufacturers);

        VehicleService vehicleService = new VehicleService();
        List<Vehicle> filteredVehicle = vehicleService.getAvailableVehicles(
                order.getStartDate(),
                order.getEndDate(),
                filter
        );

        String json = AjaxReturnFactory.getList(filteredVehicle, JsonAjaxReturn.class);
        req.setAttribute("json", json);
        return "json";
    }

    public class VehicleFilter {
        public Double minPrice;
        public Double maxPrice;
        public List<Integer> manufacturers;
    }
}
