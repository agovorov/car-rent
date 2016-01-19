package com.epam.ag.dao.impl;

import com.epam.ag.dao.VehicleDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.GalleryItem;
import com.epam.ag.model.Vehicle;
import com.epam.ag.model.dict.*;
import com.epam.ag.utils.SqlParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public class JdbcVehicleDao extends JdbcAbstractDao implements VehicleDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcVehicleDao.class);

    public JdbcVehicleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicle.isPersisted() ? update(vehicle) : insert(vehicle);
    }

    private Vehicle update(Vehicle vehicle) {
        String query = pm.get("vehicle.update");

        log.trace("SQL update statement: {}", vehicle);
        PreparedStatement ps = null;
        try {
            // Old record - UPDATE query
            ps = connection.prepareStatement(query);
            ps.setLong(1, vehicle.getManufactorId());
            ps.setString(2, vehicle.getModelId());
            ps.setInt(3, vehicle.getYear());
            ps.setLong(4, vehicle.getColorId());
            ps.setLong(5, vehicle.getBodyType());
            ps.setLong(6, vehicle.getFuel());
            ps.setLong(7, vehicle.getGearShift());
            ps.setDouble(8, vehicle.getConsumption());
            ps.setDouble(9, vehicle.getVolume());
            ps.setDouble(10, vehicle.getPrice());

            Long galleryId = vehicle.getGalleryId();
            if (galleryId == 0) {
                ps.setNull(11, Types.INTEGER);
            } else {
                ps.setLong(11, galleryId);
            }
            ps.setLong(12, vehicle.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while SQL query update", e);
        }

        return vehicle;
    }

    private Vehicle insert(Vehicle vehicle) {
        String query = pm.get("vehicle.insert");

        log.trace("SQL insert statement: {}", vehicle);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, vehicle.getManufactorId());
            ps.setString(2, vehicle.getModelId());
            ps.setInt(3, vehicle.getYear());
            ps.setLong(4, vehicle.getColorId());
            ps.setLong(5, vehicle.getBodyType());
            ps.setLong(6, vehicle.getFuel());
            ps.setLong(7, vehicle.getGearShift());
            ps.setDouble(8, vehicle.getConsumption());
            ps.setDouble(9, vehicle.getVolume());
            ps.setDouble(10, vehicle.getPrice());
            Long galleryId = vehicle.getGalleryId();
            if (galleryId == 0) {
                ps.setNull(11, Types.INTEGER);
            } else {
                ps.setLong(11, galleryId);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                log.trace("Creating fail, no rows affected.");
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            vehicle.setId(newId);
        } catch (Exception e) {
            log.error("Error while SQL query update", e);
            throw new RuntimeException("Unable to query SQL", e);
        }

        log.trace("Vehicle saved {}", vehicle);
        return vehicle;
    }

    @Override
    public Vehicle getByParameter(String param, String value) {
        log.trace("JdbcVehicleDao getByParameter");
        return null;
    }

    @Override
    public Vehicle getById(Long id) {
        log.trace("Vehicle getById statement: {}", id);
        String query = pm.get("vehicle.getByIdFull");
        PreparedStatement ps;
        Vehicle vehicle = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicle = fillFromSet(vehicle, rs);
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAll() {
        log.trace("JdbcVehicleDao getAll");
        List<Vehicle> vehicleList = new ArrayList<>();
        Statement statement = null;
        String query = pm.get("vehicle.getAllFull");

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Vehicle vehicle = null;
            while (rs.next()) {
                vehicle = fillFromSet(vehicle, rs);
                vehicleList.add(vehicle);
            }
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return vehicleList;
    }

    @Override
    public boolean delete(Vehicle entity) {
        String query = pm.get("vehicle.delete");
        log.trace("SQL delete statement: {}", entity);
        return JdbcCommonDictDao.delete(query, connection, entity);
    }

    @Override
    public List<Vehicle> getAllByParameters(Map<String, SqlParams> mapParams) {
        log.trace("Vehicle getAllByParameters");
        StringBuilder query = new StringBuilder();
        query.append(pm.get("vehicle.getAllFull"));
        int endSymbolIndex = query.indexOf(";");
        query.replace(endSymbolIndex, endSymbolIndex + 1, " ");

        int paramsCount = mapParams.size();
        if (paramsCount > 0) {
            query.append("WHERE ");
            int i = 0;
            for (Map.Entry<String, SqlParams> entry : mapParams.entrySet()) {
                i++;
                String paramName = entry.getKey();
                SqlParams sqlParams = entry.getValue();

                // TODO It`s better to use some query builder this case
                // If List then using
                if (sqlParams.list != null && !sqlParams.list.isEmpty()) {
                    // SQL IN statement
                    query.append(paramName);
                    String result = sqlParams.list.toString().replaceAll("(^\\[|\\]$)", "").replace(", ", ",");
                    query.append(" IN (" + result + ")");
                } else if (sqlParams.between != null) {
                    query.append(paramName);
                    query.append(" BETWEEN ");
                    query.append(sqlParams.between.get("min"));
                    query.append(" AND ");
                    query.append(sqlParams.between.get("max"));
                } else {
                    query.append(paramName);
                    query.append('=');
                    query.append("'" + sqlParams.value + "'");
                }
                if (i < paramsCount) {
                    if (sqlParams.expr != null) {
                        query.append(" " + sqlParams.expr + " ");
                    } else {
                        query.append(" AND ");
                    }
                }
            }
        }

        log.trace("SQL query: {}", query.toString());
        List<Vehicle> vehicleList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query.toString());
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle = fillFromSet(vehicle, rs);
                vehicleList.add(vehicle);
            }
            log.trace("{}", vehicleList);
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return vehicleList;
    }

    private Vehicle fillFromSet(Vehicle vehicle, ResultSet rs) {
        try {
            vehicle = new Vehicle();
            vehicle.setId(rs.getLong("id"));
            vehicle.setModel(rs.getString("model"));
            vehicle.setYear(rs.getInt("year"));
            vehicle.setConsumption(rs.getInt("consumption"));
            vehicle.setVolume(rs.getDouble("volume"));
            vehicle.setPrice(rs.getDouble("price"));

            // Manufacturer
            vehicle.setManufacturer(new VehicleManufacturer(
                    rs.getLong("manufacturer_id"),
                    rs.getString("manufacturer_name")
            ));


            // Color
            vehicle.setColor(new VehicleBodyColor(
                    rs.getLong("color_id"),
                    rs.getString("color_ru"),
                    rs.getString("color_en")
            ));

            // Body
            vehicle.setBodyType(new VehicleBodyType(
                    rs.getLong("body_id"),
                    rs.getString("body_ru"),
                    rs.getString("body_en")
            ));

            // Fuel
            vehicle.setFuelType(new VehicleFuelType(
                    rs.getLong("fueltype_id"),
                    rs.getString("fueltype_ru"),
                    rs.getString("fueltype_en")
            ));

            // Gearshift
            vehicle.setTransmission(new VehicleGearShift(
                    rs.getLong("gearshift_id"),
                    rs.getString("gearshift_ru"),
                    rs.getString("gearshift_en")
            ));

            // Gallery
            //vehicle.setGallery(null);
            Long galleryId = rs.getLong("gallery_id");
            Long galleryItemId = rs.getLong("gallery_item_id");
            if (galleryId > 0 && galleryItemId > 0) {
                Gallery gallery = new Gallery(galleryId);
                GalleryItem item = new GalleryItem(
                        galleryItemId,
                        rs.getBoolean("gallery_is_main"),
                        galleryId
                );
                gallery.addImage(item);
                vehicle.setGallery(gallery);
            }

        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return vehicle;
    }
}
