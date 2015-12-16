package com.epam.ag.dao.impl;

import com.epam.ag.dao.GalleryDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.Gallery;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JdbcGalleryDao  extends JdbcAbstractDao implements GalleryDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcGalleryDao.class);
//    private Connection connection;
//    private PropertiesManager pm = PropertiesManager.getInstance();

    public JdbcGalleryDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public Gallery save(Gallery entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private Gallery insert(Gallery gallery) {
        log.trace("Gallery insert statement: {}", gallery);
        String query = pm.get("gallery.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, gallery.getTitle());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            gallery.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {} ", gallery, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return gallery;
    }

    private Gallery update(Gallery gallery) {
        log.trace("Gallery update statement: {}", gallery);
        String query = pm.get("gallery.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, gallery.getTitle());
            ps.setLong(2, gallery.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}, {}", gallery, e);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return gallery;
    }

    @Override
    public Gallery getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Gallery getById(Long id) {
        log.trace("Gallery getById statement: {}", id);
        String query = pm.get("gallery.getById");
        PreparedStatement ps;
        Gallery gallery = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gallery = new Gallery();
                gallery.setId(id);
                gallery.setTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return gallery;
    }

    @Override
    public List<Gallery> getAll() {
        log.trace("Gallery getAll statement not implementing");
        return null;
    }

    @Override
    public boolean delete(Gallery entity) {
        log.trace("Gallery delete statement: {}", entity);
        String query = pm.get("gallery.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("begin transaction");
            throw new JdbcDaoException("Unable to begin transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("commit transaction");
            throw new JdbcDaoException("Unable to commit transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("rollback transaction");
            throw new JdbcDaoException("Unable to rollback transaction", e);
        }
    }

}
