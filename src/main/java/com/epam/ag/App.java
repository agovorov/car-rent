package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.lists.VehicleBodyColor;
import com.epam.ag.model.lists.VehicleFuelType;
import com.epam.ag.model.lists.VehicleGearShift;
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
 *
 *
 * Questions:
 *  1 FuelType, GearShift - Enum?
 *
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setManufacturer(new VehicleManufacturer("BMW"));
        vehicle.setModel("X5");
        vehicle.setYear(2005);
        vehicle.setTransmission(VehicleGearShift.MANUAL);
        vehicle.setFuelType(VehicleFuelType.GASOLINE);
        vehicle.setConsumption(12.5);
        vehicle.setVolume(3.0);
        vehicle.setColor(VehicleBodyColor.);


        DaoFactory daoFactory = DaoFactory.getInstance();

        // Получаем DAO и совершаем зло
        VehicleDao vehicleDao = daoFactory.getDao(VehicleDao.class);
        UserDao userDao = daoFactory.getDao(UserDao.class);

        // Test query
        vehicle = vehicleDao.save(vehicle);
    }
}
