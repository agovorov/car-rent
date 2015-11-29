package com.epam.ag;

import com.epam.ag.dao.*;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.VehicleBodyTypeDao;
import com.epam.ag.model.dict.*;
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
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);

//        VehicleGearShift entity = new VehicleGearShift("Робот", "Robot");
//        entity = dao.save(entity);

        VehicleGearShift entity = new VehicleGearShift();
        entity = dao.getById(3L);
        entity.setValues("ttt", "yyy");
        entity = dao.save(entity);


        //VehicleBodyColor entity = new VehicleBodyColor();
        System.out.println(entity);
//
        boolean delete = dao.delete(entity);
//        System.out.println(delete);

       // List<VehicleBodyColor> list = dao.getAll();
        //System.out.println(list);
    }
}
