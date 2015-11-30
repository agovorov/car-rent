package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.UserRoleDao;
import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
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
 *         <p/>
 *         <p/>
 *         Questions:
 *         1. Price/day где лучше? (поле или отдельный класс)
 *         2. User -> Passport -> Address - ?
 *         3. Что должен возвращать entity.getColor(), чтобы потом работать с БД: id или name?
 *         4. Set... в prepareStatement, SetString или все set...?
 *         5. Где описана работа с транзакциями? Dao? JdbcDao?
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
        UserRoleDao dao = daoFactory.getDao(UserRoleDao.class);

        UserRole role = new UserRole();
        role = dao.getById(3L);

        dao.delete(role);
        /*
        User user = new User();
        user.setEmail("admin@admin.ru");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("+1 555 32 45 44");
        user.setPassword("secret");
        user.setRole(new UserRole("ADMIN"));

        user = dao.save(user);
        */
        System.out.println(role);
    }
}
