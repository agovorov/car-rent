package com.epam.ag.action;

import com.epam.ag.action.helpers.AuthManager;
import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.Vehicle;
import com.epam.ag.service.UserService;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.DateConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class OrderTempAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        VehicleService vs = new VehicleService();
        Vehicle vehicle = vs.getVehicleById(55L);

        UserService us = new UserService();
        User user = us.getUserByEmail("admin@admin.ru");

        Order order = new Order();
        order.setStartDate(DateConverter.strToDate("01.12.2015"));
        order.setEndDate(DateConverter.strToDate("06.12.2015"));
        order.setVehicle(vehicle);
        order.setCustomer(user);

        // Авторизуем пользователя
        AuthManager.authorize(user, req);

        req.getSession(true).setAttribute("order", order);
        return "redirect:controller?action=order-verify&id=" + vehicle.getId();
    }
}
