package com.epam.ag.dao.impl;

import com.epam.ag.dao.AddressDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.user.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcAddressDao extends JdbcAbstractDao implements AddressDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcAddressDao.class);

    public JdbcAddressDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public Address save(Address entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private Address insert(Address address) {
        log.trace("Address insert statement: {}", address);
        String query = pm.get("address.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getAppartmentNumber());
            ps.setString(5, address.getStreetNumber());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            address.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {} ", address, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return address;
    }

    private Address update(Address address) {
        log.trace("User update statement: {}", address);
        String query = pm.get("address.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getAppartmentNumber());
            ps.setString(5, address.getStreetNumber());
            ps.setLong(6, address.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return address;
    }

    @Override
    public Address getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Address getById(Long id) {
        log.trace("User getById statement: {}", id);
        String query = pm.get("address.getById");
        PreparedStatement ps;
        Address address = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                address = new Address(id);
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setStreetNumber(rs.getString("streetNumber"));
                address.setAppartmentNumber(rs.getInt("appartmentNumber"));
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }

        return address;
    }

    @Override
    public List<Address> getAll() {
        log.trace("User getAll statement not implementing");
        return null;
    }

    @Override
    public boolean delete(Address entity) {
        log.trace("User delete statement: {}", entity);
        String query = pm.get("address.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }
}
