package com.epam.ag.model;

import com.epam.ag.model.lists.*;

/**
 */
public class Vehicle extends BaseEntity {

    private VehicleBodyColor vehicleBodyColor;
    private VehicleBodyType vehicleBodyType;
    private VehicleManufacturer manufacturer;
    private VehicleFuelType vehicleFuelType;
    private VehicleGearShift vehicleGearShift;
    private String vehicleModel;
    private int manufacturYear;
    private boolean isAvailable = true;
    private double maxSpeed;
    private double averageConsumption;
    private int maxSeatsCount;


    public void setManufacturer(VehicleManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setYear(int manufacturYear) {
        this.manufacturYear = manufacturYear;
    }
}
