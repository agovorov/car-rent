package com.epam.ag.model;

import com.epam.ag.model.dict.*;

import java.util.ArrayList;
import java.util.List;

public class Vehicle extends BaseEntity {
    private VehicleBodyColor vehicleBodyColor;
    private VehicleBodyType vehicleBodyType;
    private VehicleManufacturer manufacturer;
    private VehicleFuelType vehicleFuelType;
    private VehicleGearShift vehicleGearShift;
    private String model;
    private int year;
    private boolean isAvailable = true;
    private double averageConsumption;
    private double volume;
    private List<VehicleImage> vehicleImages;

    public Vehicle() {
        vehicleImages = new ArrayList<>();
    }

    public void setManufacturer(VehicleManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String vehicleModel) {
        this.model = vehicleModel;
    }

    public void setYear(int manufacturyYear) {
        this.year = manufacturyYear;
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

    public void setBodyType(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
    }

    public Long getManufactorId() {
        return manufacturer.getId();
    }

    public String getModelId() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Long getColorId() {
        return vehicleBodyColor.getId();
    }

    public long getBodyType() {
        return vehicleBodyType.getId();
    }

    public long getFuel() {
        return vehicleFuelType.getId();
    }

    public long getGearShift() {
        return vehicleGearShift.getId();
    }

    public double getConsumption() {
        return averageConsumption;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ",\n ID='" + id + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", year=" + year +
                ", volume=" + volume +
                ", averageConsumption=" + averageConsumption +
                ", isAvailable=" + isAvailable +
                ", vehicleBodyColor=" + vehicleBodyColor +
                ", vehicleBodyType=" + vehicleBodyType +
                ", vehicleFuelType=" + vehicleFuelType +
                ", vehicleGearShift=" + vehicleGearShift +
                ", vehicleImages=" + vehicleImages +
                '}';
    }
}
