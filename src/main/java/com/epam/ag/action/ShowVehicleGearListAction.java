package com.epam.ag.action;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleBodyTypeDao;
import com.epam.ag.dao.VehicleGearShiftDao;
import com.epam.ag.model.dict.VehicleBodyType;
import com.epam.ag.model.dict.VehicleGearShift;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowVehicleGearListAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // Read color list
        DaoFactory daoFactory = DaoFactory.getInstance();
        VehicleGearShiftDao dao = daoFactory.getDao(VehicleGearShiftDao.class);
        List<VehicleGearShift> gearShifts = dao.getAll();

        req.setAttribute("gearShifts", gearShifts);
        return "admin/vehicle-gear-list";
    }
}
