package com.epam.ag.model.dict;

/**
 * Govorov Andrey
 */

public class VehicleBodyColor extends DictionaryBase {

    public VehicleBodyColor(String value) {
        super(value);
    }

    public VehicleBodyColor(Long id, String value) {
        super(id, value);
    }

    public VehicleBodyColor(Long id, String value_ru, String value_en) {
        super(id, value_ru, value_en);
    }

    public VehicleBodyColor(String value_ru, String value_en) {
        super(value_ru, value_en);
    }

    public VehicleBodyColor() {

    }
}