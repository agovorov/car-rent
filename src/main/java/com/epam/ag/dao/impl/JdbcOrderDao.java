package com.epam.ag.dao.impl;

import com.epam.ag.dao.OrderDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.Order;
import com.epam.ag.model.User;
import com.epam.ag.model.Vehicle;
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
public class JdbcOrderDao extends JdbcAbstractDao implements OrderDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcOrderDao.class);

    public JdbcOrderDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public Order save(Order entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private Order insert(Order order) {
        log.trace("Order insert statement: {}", order);
        String query = pm.get("order.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, JdbcHelper.convertToSQLDate(order.getStartDate()));
            ps.setDate(2, JdbcHelper.convertToSQLDate(order.getEndDate()));
            ps.setDate(3, JdbcHelper.convertToSQLDate(order.getDateOfOrder()));
            ps.setLong(4, order.getVehicle().getId());
            ps.setLong(5, order.getCustomer().getId());
            ps.setString(6, order.getStatus().name());
            ps.setString(7, order.getCauseOfFailure());
            ps.setString(8, order.getDamageNote());
            ps.setInt(9, order.getDamagePrice());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            order.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return order;
    }

    private Order update(Order order) {
        log.trace("Order update statement: {}", order);
        String query = pm.get("order.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, JdbcHelper.convertToSQLDate(order.getStartDate()));
            ps.setDate(2, JdbcHelper.convertToSQLDate(order.getEndDate()));
            ps.setDate(3, JdbcHelper.convertToSQLDate(order.getDateOfOrder()));
            ps.setLong(4, order.getVehicle().getId());
            ps.setLong(5, order.getCustomer().getId());
            ps.setString(6, order.getStatus().name());
            ps.setString(7, order.getCauseOfFailure());
            ps.setString(8, order.getDamageNote());
            ps.setInt(9, order.getDamagePrice());
            ps.setLong(10, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return order;
    }

    @Override
    public Order getByParameter(String param, String value) {
        return null;
    }

    @Override
    public List<Order> getAllByParameters(Map<String, SqlParams> mapParams) {
        log.trace("Order getAllByParameters");
        StringBuilder query = new StringBuilder();
        query.append(pm.get("order.getAll"));
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
                    String result = sqlParams.list.toString()
                            .replaceAll("(^\\[|\\]$)", "")
                            .replace(", ", "','");
                    query.append(" IN ('" + result + "')");
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

        // TODO It`s no correct but I`ve no query builder now
        query.append(" ORDER BY id DESC;");

        log.trace("SQL query: {}", query.toString());
        List<Order> orderList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query.toString());
            while (rs.next()) {
                Order order = new Order();
                order = fillFromSet(order, rs);
                orderList.add(order);
            }
            log.trace("{}", orderList);
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return orderList;
    }


    @Override
    public Order getById(Long id) {
        log.trace("Order statement: {}", id);
        String query = pm.get("order.getById");

        PreparedStatement ps;
        Order order = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order = fillFromSet(order, rs);
            }
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {} ", order, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        log.trace("Order getAll");
        String query = pm.get("order.getAll");
        List<Order> orderList = new ArrayList<>();
        Statement statement = null;
        Order order;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                order = new Order();
                order = fillFromSet(order, rs);
                orderList.add(order);
            }
            log.trace("{}", orderList);
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return orderList;
    }

    @Override
    public boolean delete(Order entity) {
        log.trace("JdbcOrderDao delete");
        String query = pm.get("order.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }

    private Order fillFromSet(Order order, ResultSet recordSet) {
        try {
            // Create vehicle
            Vehicle vehicle = new Vehicle(recordSet.getLong("vehicle_id"));

            // Create user
            User user = new User(recordSet.getLong("user_id"));

            // Status
            Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(recordSet.getString("status"));

            order.setId(recordSet.getLong("id"));
            order.setStartDate(recordSet.getDate("date_start"));
            order.setEndDate(recordSet.getDate("date_end"));
            order.setDateOfOrder(recordSet.getDate("date_order"));
            order.setStatus(orderStatus);
            order.setVehicle(vehicle);
            order.setCustomer(user);
            order.setCauseOfFailure(recordSet.getString("cause"));
            order.setDamageNote(recordSet.getString("damage_note"));
            order.setDamagePrice(recordSet.getInt("damage_price"));

        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return order;
    }
}
