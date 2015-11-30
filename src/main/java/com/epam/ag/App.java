package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.UserRoleDao;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        /*
        Vehicle vehicle = new Vehicle();
        vehicle.setModel("X5");
        vehicle.setYear(2005);
        vehicle.setConsumption(12.5);
        vehicle.setVolume(3.0);

        // Как правильно делать? Откуда брать ID пока его нет
        // Точнее даже, что делать при сохранении такой конструкции в JdbcVehicleDao
        vehicle.setColor(new VehicleBodyColorDao(1L, "Серый"));
        vehicle.setFuelType(new VehicleFuelTypeDao(1L, "Бензин"));
        vehicle.setBodyType(new VehicleBodyType(1L, "Кросссовер"));
        vehicle.setManufacturer(new VehicleManufacturer(1L, "BMW"));
        vehicle.setTransmission(new VehicleGearShift(1L, "Автомат"));


        DaoFactory daoFactory = DaoFactory.getInstance();
        // Получаем DAO и совершаем зло
        VehicleDao vehicleDao = daoFactory.getDao(VehicleDao.class);
//        // UserDao userDao = daoFactory.getDao(UserDao.class);
//
//        // Test query
        log.trace("Is new: {}", vehicle.isPersisted());
        vehicle = vehicleDao.save(vehicle);
        log.trace("Is new: {}", vehicle.isPersisted());*/

        // Add new manufacture
        DaoFactory daoFactory = DaoFactory.getInstance();
        //VehicleBodyTypeDao dao = daoFactory.getDao(VehicleBodyTypeDao.class);
        UserDao dao = daoFactory.getDao(UserDao.class);


        User user = new User();
        /*user.setEmail("client@client.ru");
        user.setFirstName("John3");
        user.setLastName("Doe3");
        user.setPhone("+1 555 11 11 11");
        user.setPassword("secret");
        */

        user = dao.getById(1L);
        System.out.println(user);

        //System.out.println(user);
    }
}
