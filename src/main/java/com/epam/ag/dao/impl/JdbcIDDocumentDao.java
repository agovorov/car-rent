package com.epam.ag.dao.impl;

import com.epam.ag.dao.IDDocumentDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.User;
import com.epam.ag.model.user.Address;
import com.epam.ag.model.user.IDDocument;
import com.epam.ag.model.user.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class JdbcIDDocumentDao extends JdbcAbstractDao implements IDDocumentDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcIDDocumentDao.class);

    public JdbcIDDocumentDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public IDDocument save(IDDocument entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private IDDocument insert(IDDocument document) {
        log.trace("insert statement: {}", document);
        String query = pm.get("idDocument.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, document.getOwnerId());
            ps.setDate(2, JdbcHelper.convertToSQLDate(document.getIssueDate()));
            ps.setDate(3, JdbcHelper.convertToSQLDate(document.getExpirationDate()));
            ps.setString(4, document.getIssuePlace());

            Long livingAddress = document.getLivingAddressId();
            if (livingAddress == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setLong(5, livingAddress);
            }
            ps.setString(6, document.getDocumentNumber());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Добавляем ключ
            Long newId = JdbcHelper.getReturningID(ps);
            document.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return document;
    }

    private IDDocument update(IDDocument document) {
        log.trace("update statement: {}", document);
        String query = pm.get("idDocument.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, document.getOwnerId());
            ps.setDate(2, (Date) document.getIssueDate());
            ps.setDate(3, (Date) document.getExpirationDate());
            ps.setString(4, document.getIssuePlace());
            Long livingAddress = document.getLivingAddressId();
            if (livingAddress == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setLong(5, livingAddress);
            }
            ps.setString(6, document.getDocumentNumber());
            ps.setLong(7, document.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {}", document, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return document;
    }

    @Override
    public IDDocument getByParameter(String param, String value) {
        return null;
    }

    @Override
    public IDDocument getById(Long id) {
        return null;
    }

    @Override
    public List<IDDocument> getAll() {
        return null;
    }

    @Override
    public boolean delete(IDDocument entity) {
        return false;
    }

    @Override
    public IDDocument getByUser(User user) {
        Long userId = user.getId();
        log.trace("Document getByUser statement: {}", userId);
        String query = pm.get("idDocument.getByUserId");
        PreparedStatement ps;
        IDDocument document = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                document = fillFromSet(document, rs);
                user.setPassport(document);
            }
        } catch (SQLException e) {
            log.error("getByUser: {}", userId);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return document;
    }

    private IDDocument fillFromSet(IDDocument document, ResultSet rs) {
        try {
            document = new Passport();
            document.setId(rs.getLong("id"));
            document.setSeries(rs.getString("series"));
            document.setDocumentNumber(rs.getString("doc_number"));
            document.setIssuePlace(rs.getString("place_of_issue"));
            document.setIssueDate(rs.getDate("date_of_issue"));
            document.setExpirationDate(rs.getDate("expiration_date"));
            document.setOwner(new User(rs.getLong("owner_id")));

            Address address = new Address(rs.getLong("address_id"));
            address.setCountry(rs.getString("country"));
            address.setCity(rs.getString("city"));
            address.setStreet(rs.getString("street"));
            address.setStreetNumber(rs.getString("appartment_number"));
            address.setAppartmentNumber(rs.getInt("street_number"));
            document.setAddress(address);
        } catch (SQLException e) {
            log.error("Error while SQL query select", e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return document;
    }
}
