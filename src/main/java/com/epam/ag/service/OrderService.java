package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.OrderDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.VehicleManufacturer;
import com.epam.ag.service.exception.OrderServiceException;
import com.epam.ag.utils.SqlParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Govorov Andrey
 */
public class OrderService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {

        List<Vehicle> list;

        VehicleService vehicleService = new VehicleService();
        list = vehicleService.getVehicleList();

        return list;
    }

    public boolean saveOrder(Order order) {
        daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.getDao(OrderDao.class);

        try {
            orderDao.save(order);
        } catch (DaoException e) {
            log.error("Unable to save order", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public Order getOrderById(long orderId) {
        daoFactory = DaoFactory.getInstance();
        OrderDao dao = daoFactory.getDao(OrderDao.class);
        Order order = dao.getById(orderId);
        daoFactory.close();
        return order;
    }

    /**
     * Load data for vehicle
     */
    public void loadVehicleData(Order order) {
        Long vehicleId = order.getVehicle().getId();
        if (vehicleId == null) {
            log.trace("Unable to load data for order. Vehicle id is not specified: {}", order);
            throw new OrderServiceException("Unable to load data for order. Vehicle id is not specified.");
        }

        VehicleService vehicleService = new VehicleService();
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            log.trace("Unable to load data for order. Vehicle not found: {}", order);
            throw new OrderServiceException("Unable to load data for order. Vehicle not found!");
        }

        order.setVehicle(vehicle);
    }

    /**
     * Load data about customer
     *
     * @param order
     */
    public void loadCustomerData(Order order) {
        Long userId = order.getCustomer().getId();
        if (userId == null) {
            log.trace("Unable to load data for order. User id is not specified: {}", order);
            throw new OrderServiceException("Unable to load data for order. User id is not specified.");
        }

        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        userService.loadPassportData(user);
        if (user == null) {
            log.trace("Unable to load data for order. User not found: {}", order);
            throw new OrderServiceException("Unable to load data for order. User not found!");
        }

        order.setCustomer(user);
    }

    public boolean deleteById(Long orderId) {
        daoFactory = DaoFactory.getInstance();
        OrderDao dao = daoFactory.getDao(OrderDao.class);
        Order order = dao.getById(orderId);
        if (order == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(order);
        daoFactory.close();
        return isDeleted;
    }

    public List<Order> getOrdersList(Map params) {
        daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.getDao(OrderDao.class);

        List<Order> orderList = orderDao.getAllByParameters(params);
        daoFactory.close();
        return orderList;
    }

    public List<Order> getUsersOrderList(User user, Map<String, SqlParams> params) {
        List orderList = new ArrayList();
        if (user == null) {
            return orderList;
        }

        daoFactory = DaoFactory.getInstance();
        OrderDao dao = daoFactory.getDao(OrderDao.class);

        if (params == null) {
            params = new HashMap<>();
        }
        params.put("user_id", new SqlParams(user.getId()));
        orderList = dao.getAllByParameters(params);
        daoFactory.close();

        return orderList;
    }

}
