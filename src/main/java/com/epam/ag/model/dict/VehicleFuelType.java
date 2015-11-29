package com.epam.ag.model.dict;

public class VehicleFuelType extends DictionaryBase {

    public VehicleFuelType(String value) {
        super(value);
    }

    public VehicleFuelType(Long id, String value) {
        super(id, value);
    }

    public VehicleFuelType(String value_ru, String value_en) {
        super(value_ru, value_en);
    }

    public VehicleFuelType(Long id, String value_ru, String value_en) {
        super(id, value_ru, value_en);
    }
    public VehicleFuelType() {

    }
}