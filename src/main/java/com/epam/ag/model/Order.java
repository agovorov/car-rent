package com.epam.ag.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Govorov Andrey
 */
public class Order extends BaseEntity {

    private User customer;
    private Date dateOfBeginning;
    private Date dateOfEnding;
    private Date dateOfOrder;
    private Vehicle vehicle;

    private String causeOfFailure;
    private String damageNote;
    private int damagePrice;
    private OrderStatus orderStatus;

    public int getDamagePrice() {
        return damagePrice;
    }

    public void setDamagePrice(int damagePrice) {
        this.damagePrice = damagePrice;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Date getStartDate() {
        return dateOfBeginning;
    }

    public void setStartDate(Date dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public Date getEndDate() {
        return dateOfEnding;
    }

    public void setEndDate(Date dateOfEnding) {
        this.dateOfEnding = dateOfEnding;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * Number of days for ordering
     *
     * @return
     */
    public int countDays() {
        long diff = dateOfEnding.getTime() - dateOfBeginning.getTime();
        // Last day is not counting. Add this manually
        return (int) TimeUnit.MILLISECONDS.toDays(diff) + 1;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                "vehicle=" + vehicle +
                ", dateOfBeginning=" + dateOfBeginning +
                ", dateOfEnding=" + dateOfEnding +
                ", dateOfOrder=" + dateOfOrder +
                '}';
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getCauseOfFailure() {
        return causeOfFailure;
    }

    public void setCauseOfFailure(String causeOfFailure) {
        this.causeOfFailure = causeOfFailure;
    }

    public String getDamageNote() {

        return damageNote;
    }

    public void setDamageNote(String damageNote) {
        this.damageNote = damageNote;
    }


    /**
     * Inner enum for status
     */
    public enum OrderStatus {
        WAITING,         // Ожидает подтверждение админом
        CONFIRMED,       // Подтвержден админом, ожидает оплаты
        PAYED,           // Оплачен
        VEHICLE_TAKEN,   // Авто забрали со стоянки
        VEHICLE_RECEIVED,// Вернули
        CLOSED,          // Все закончили
        DENIED,          // Отклонено
        DAMAGED          // Есть повреждения, ожидаем оплату
    }
}
