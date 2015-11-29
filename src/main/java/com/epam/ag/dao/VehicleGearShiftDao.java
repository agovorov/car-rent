package com.epam.ag.dao;


import com.epam.ag.model.dict.VehicleGearShift;

import java.util.List;

public interface VehicleGearShiftDao extends GenericDao<VehicleGearShift> {
    @Override
    VehicleGearShift save(VehicleGearShift entity);

    @Override
    VehicleGearShift getByParameter(String param, String value);

    @Override
    VehicleGearShift getById(Long id);

    @Override
    List<VehicleGearShift> getAll();

    @Override
    boolean delete(VehicleGearShift entity);

}
