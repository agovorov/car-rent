package com.epam.ag.model.dict;

import com.epam.ag.model.Vehicle;

public class VehicleGearShift extends DictionaryBase{

    public VehicleGearShift(String value) {
        super(value);
    }

    public VehicleGearShift(Long id, String value) {
        super(id, value);
    }

    public VehicleGearShift(String value_ru,String value_en) {
        super(value_ru, value_en);
    }

    public VehicleGearShift(Long id, String value_ru, String value_en) {
        super(id, value_ru, value_en);
    }

    public VehicleGearShift() {

    }

    public VehicleGearShift(long id) {
        super(id);
    }
}