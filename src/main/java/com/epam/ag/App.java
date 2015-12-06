package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.GalleryItem;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.*;
import com.epam.ag.service.GalleryService;
import com.epam.ag.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 15.	Система Прокат автомобилей. Клиент выбирает Автомобиль из списка доступных. Заполняет
 * форму Заказа, указывая паспортные данные, срок аренды. Клиент оплачивает Заказ.
 * Администратор регистрирует возврат автомобиля. В случае повреждения Автомобиля,
 * Администратор вносит информацию и выставляет счет за ремонт. Администратор может
 * отклонить Заявку, указав причины отказа.
 * <p/>
 * - affectedRows Надо ли?
 *
 * @author Govorov Andrey
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // Load data
//        Gallery gallery = new Gallery();
//        GalleryService galleryService = new GalleryService();
//        gallery = galleryService.get(18L, gallery);
//        VehicleBodyColor color = new VehicleBodyColor(1L, "Серый");
//        VehicleFuelType fuel = new VehicleFuelType(1L, "Бензин");
//        VehicleBodyType body = new VehicleBodyType(1L, "Кроссовер");
//        VehicleManufacturer manufacturer = new VehicleManufacturer(2L, "BMW");
//        VehicleGearShift gearShift = new VehicleGearShift(1L, "Автомат");

        Vehicle vehicle = new Vehicle();
//        vehicle.setModel("X5");
//        vehicle.setYear(2005);
//        vehicle.setConsumption(12.5);
//        vehicle.setVolume(3.0);
//        vehicle.setPrice(15000.0);
//        vehicle.setColor(color);
//        vehicle.setFuelType(fuel);
//        vehicle.setBodyType(body);
//        vehicle.setManufacturer(manufacturer);
//        vehicle.setTransmission(gearShift);
//        vehicle.setGallery(gallery);

//        DaoFactory daoFactory = DaoFactory.getInstance();
//        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
//        vehicle = dao.save(vehicle);

//        VehicleService vehicleService = new VehicleService();
//        vehicle = vehicleService.getEntireEntity(51L);
//        System.out.println(vehicle);

        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleManufacturerDao dao = daoFactory.getDao(VehicleManufacturerDao.class);
        List<VehicleManufacturer> manufacturers = dao.getAll();
        System.out.println(manufacturers);


//        VehicleService vehicleService = new VehicleService();
//        vehicle = vehicleService.save(vehicle, true);
    }
}
