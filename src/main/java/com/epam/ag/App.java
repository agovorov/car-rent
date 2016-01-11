package com.epam.ag;

import com.epam.ag.model.Order;
import com.epam.ag.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//        filter.minPrice = 100.0;
//        filter.maxPrice = 10000.0;
//        filter.manufacturers =
//        List manufaturers = new ArrayList<>(Arrays.asList(10));
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("manufacturer_id", new SqlParams(manufaturers));
//
//        VehicleService vehicleService = new VehicleService();
//        List<Vehicle> filteredVehicle = vehicleService.getVehicleList(map);
//
//        if (!filteredVehicle.isEmpty()) {
//            for (Vehicle v : filteredVehicle) {
//                log.trace("V: {}", v);
//            }
//        } else {
//            log.trace("Empty");
//        }

        OrderService service = new OrderService();
        Order order = service.getOrderById(10L);
        service.loadVehicleData(order);
        service.loadCustomerData(order);
        log.trace("Order: {}", order.getCustomer().getPassport().getLivingAddress().getCountry());
//        order.getCustomer().getPassport().getLivingAddress().getCountry()

//
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        IDDocumentDao documentDao = daoFactory.getDao(IDDocumentDao.class);
//        IDDocument passport = documentDao.getByUser(11L);
//        daoFactory.close();

//        log.trace("doc: {}", passport);
//        log.trace("D: {}", order.getCustomer().getPassport());
    }
}


