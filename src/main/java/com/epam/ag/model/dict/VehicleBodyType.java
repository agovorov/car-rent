package com.epam.ag.model.dict;

/**
 * Govorov Andrey
 */
public class VehicleBodyType extends DictionaryBase {

    public VehicleBodyType() {

    }
    public VehicleBodyType(String value) {
        super(value);
    }

    public VehicleBodyType(Long id, String value) {
        super(id, value);
    }

    public  VehicleBodyType(String valueRu, String valueEn) {
        super(valueRu, valueEn);
    }

    public VehicleBodyType(Long id, String valueRu, String valueEn) {
        super(id, valueRu, valueEn);
    }

    public VehicleBodyType(Long id) {
        super(id);
    }
}
