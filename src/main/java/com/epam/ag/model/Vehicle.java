package com.epam.ag.model;

import com.epam.ag.model.dict.*;

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
    //private List<GalleryItem> vehicleImages;
    //private Gallery<GalleryItem> vehicleImages = new Gallery<>();
    private Gallery vehicleImages = new Gallery();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // TODO В идеале это кандидат на вынос в другую таблицу, тогда и история будет.
    // сейчас разрешили тут разместить
    private double price;

    public void setManufacturer(VehicleManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String vehicleModel) {
        this.model = vehicleModel;
    }

    public void setTransmission(VehicleGearShift vehicleGearShift) {
        this.vehicleGearShift = vehicleGearShift;
    }

    public void setFuelType(VehicleFuelType vehicleFuelType) {
        this.vehicleFuelType = vehicleFuelType;
    }

    public void setColor(VehicleBodyColor vehicleBodyColor) {
        this.vehicleBodyColor = vehicleBodyColor;
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

    public void setYear(int manufacturyYear) {
        this.year = manufacturyYear;
    }

    public Long getColorId() {
        return vehicleBodyColor.getId();
    }

    public long getBodyType() {
        return vehicleBodyType.getId();
    }

    public void setBodyType(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
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

    public void setConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setGallery(Gallery gallery) {
        vehicleImages = gallery;
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

    public long getGalleryId() {
        return vehicleImages.getId();
    }
}
