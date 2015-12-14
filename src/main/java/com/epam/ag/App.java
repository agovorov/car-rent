package com.epam.ag;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.dao.VehicleManufacturerDao;
import com.epam.ag.model.*;
import com.epam.ag.model.dict.*;
import com.epam.ag.service.GalleryService;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 15.	Система Прокат автомобилей. Клиент выбирает Автомобиль из списка доступных. Заполняет
 * форму Заказа, указывая паспортные данные, срок аренды. Клиент оплачивает Заказ.
 * Администратор регистрирует возврат автомобиля. В случае повреждения Автомобиля,
 * Администратор вносит информацию и выставляет счет за ремонт. Администратор может
 * отклонить Заявку, указав причины отказа.
 * <p/>
 * - В vehicle NPE если не сетаею
 *
 * @author Govorov Andrey
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Map<String, String> map = new TreeMap<>();

//        map.put("v-manufactor", "3434343");
//        map.put("v-model", "bs");
//        map.put("v-year", "asas");
        map.put("v-volume", "443.5");

        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateMap(map);
        if (!systemMessage.getErrors().isEmpty()) {
            System.out.println("----------------------------");
            System.out.println("Errors found");
            System.out.println(systemMessage.getErrors());
        } else {
            System.out.println("All ok");
        }
//        SystemMessage systemMessage = new SystemMessage("Error found!", SystemMessage.ERROR);
//        systemMessage.addError("user-name", "username.wrong-length");
//        systemMessage.addError("user-passwd", "username.pass.too-short");
//        systemMessage.addError("user-passwd", "username.pass.required");
//        systemMessage.addError("user-date", "username.date.wrong-date-format");
//
//        System.out.println( systemMessage.getType() );

        //SystemMessage.ERROR



        // Load data
//        Gallery gallery = new Gallery();
//        GalleryService galleryService = new GalleryService();
//        gallery = galleryService.get(18L, gallery);
//        VehicleBodyColor color = new VehicleBodyColor(1L, "Серый");
//        VehicleFuelType fuel = new VehicleFuelType(1L, "Бензин");
//        VehicleBodyType body = new VehicleBodyType(1L, "Кроссовер");
//        VehicleManufacturer manufacturer = new VehicleManufacturer(2L, "BMW");
//        VehicleGearShift gearShift = new VehicleGearShift(1L, "Автомат");

//        Vehicle vehicle = new Vehicle();
//
//        vehicle.setModel("X5");
//        vehicle.setYear(2005);
//        vehicle.setConsumption(12.5);
//        vehicle.setVolume(3.0);
//        vehicle.setPrice(15000.0);
//        vehicle.setColor(new VehicleBodyColor(1L));
//        //vehicle.setFuelType(new VehicleFuelType(1L));
//        vehicle.setBodyType(new VehicleBodyType(10L));
//        vehicle.setManufacturer(new VehicleManufacturer(2L));
//        vehicle.setTransmission(new VehicleGearShift(1L));
//        vehicle.setGallery(new Gallery(1L));

//        DaoFactory daoFactory = DaoFactory.getInstance();
//        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
//
//        vehicle = dao.getById(55L);
//        vehicle.setGallery(new Gallery(1L));
//        vehicle.setVolume(1.7);
//        vehicle.setModel("100");
//        vehicle = dao.save(vehicle);



//        VehicleService vehicleService = new VehicleService();
//        vehicle = vehicleService.getEntireEntity(51L);
//        System.out.println(vehicle);
//
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
//        List<Vehicle> vehicles = dao.getAll();
//
//        for (Vehicle v: vehicles) {
//            System.out.println(v.getManufactorId());
//        }


//        VehicleService vehicleService = new VehicleService();
//        vehicle = vehicleService.save(vehicle, true);


    }
}
