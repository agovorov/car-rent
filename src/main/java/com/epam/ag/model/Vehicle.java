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
    private double averageConsumption;
    private double volume;


    public void setManufacturer(VehicleManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setYear(int manufacturYear) {
        this.manufacturYear = manufacturYear;
    }

    public void setTransmission(VehicleGearShift vehicleGearShift) {
        this.vehicleGearShift = vehicleGearShift;
    }

    public void setFuelType(VehicleFuelType vehicleFuelType) {
        this.vehicleFuelType = vehicleFuelType;
    }

    public void setConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setColor(VehicleBodyColor vehicleBodyColor) {
        this.vehicleBodyColor = vehicleBodyColor;
    }

    public void setColor(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
    }
}
