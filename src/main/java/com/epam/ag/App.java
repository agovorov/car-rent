package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.GenericDao;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.BaseEntity;
import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.lists.VehicleManufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 15.	Система Прокат автомобилей. Клиент выбирает Автомобиль из списка доступных. Заполняет
 * форму Заказа, указывая паспортные данные, срок аренды. Клиент оплачивает Заказ.
 * Администратор регистрирует возврат автомобиля. В случае повреждения Автомобиля,
 * Администратор вносит информацию и выставляет счет за ремонт. Администратор может
 * отклонить Заявку, указав причины отказа.
 *
 * @author Govorov Andrey
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setManufacturer(new VehicleManufacturer("BMW"));
        vehicle.setModel("X5");
        vehicle.setYear(2005);

        DaoFactory daoFactory = DaoFactory.getInstance();

        // Получаем DAO и совершаем зло
        VehicleDao vehicleDao = daoFactory.getDao(VehicleDao.class);
        UserDao userDao = daoFactory.getDao(UserDao.class);

        // Test query
        vehicle = vehicleDao.save(vehicle);
    }
}
