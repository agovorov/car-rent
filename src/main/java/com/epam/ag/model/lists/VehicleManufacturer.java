package com.epam.ag.model.lists;

import com.epam.ag.model.BaseEntity;

/**
 * List of manufacturers
 *
 * @author Govorov Andrey
 */
public class VehicleManufacturer extends BaseEntity {

    private String vehicleManufacturer;

    public VehicleManufacturer(String manufacturer) {
        vehicleManufacturer = manufacturer;
    }
}
