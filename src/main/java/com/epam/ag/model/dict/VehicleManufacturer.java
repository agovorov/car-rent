package com.epam.ag.model.dict;

/**
 * List of manufacturers
 *
 * @author Govorov Andrey
 */
public class VehicleManufacturer extends DictionaryBase {

    public VehicleManufacturer(String value) {
        super(value);
    }

    public VehicleManufacturer(Long id, String value) {
        super(id, value);
    }

    public VehicleManufacturer() {
        super();
    }

    public String getTitle(){
        return getValue();
    }
}
