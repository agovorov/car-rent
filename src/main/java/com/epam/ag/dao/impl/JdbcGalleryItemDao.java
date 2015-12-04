package com.epam.ag.dao.impl;

import com.epam.ag.dao.GalleryItemDao;
import com.epam.ag.dao.impl.exception.JdbcDaoException;
import com.epam.ag.model.GalleryItem;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGalleryItemDao implements GalleryItemDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcGalleryItemDao.class);
    private Connection connection;
    private PropertiesManager pm = PropertiesManager.getInstance();

    public JdbcGalleryItemDao(Connection connection) {
        this.connection = connection;
        pm.loadPropertyFile("query.properties");
    }

    @Override
    public GalleryItem save(GalleryItem entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    private GalleryItem insert(GalleryItem item) {
        log.trace("Gallery item insert statement: {}", item);
        String query = pm.get("galleryItem.insert");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getGalleryId());
            ps.setString(2, item.getFullImagePath());
            ps.setString(3, item.getThumbPath());
            ps.setInt(4, (item.isMainImage() ? 1 : 0));
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fail, no rows affected.");
            }

            // Updating ID
            Long newId = JdbcHelper.getReturningID(ps);
            item.setId(newId);
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", item);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return item;
    }

    private GalleryItem update(GalleryItem item) {
        log.trace("Gallery item update statement: {}", item);
        String query = pm.get("galleryItem.update");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, item.getGalleryId());
            ps.setString(2, item.getFullImagePath());
            ps.setString(3, item.getThumbPath());
            ps.setInt(4, (item.isMainImage() ? 1 : 0));
            ps.setLong(5, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to query SQL {}", item);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return item;
    }

    @Override
    public GalleryItem getByParameter(String param, String value) {
        return null;
    }

    @Override
    public GalleryItem getById(Long id) {
        log.trace("getById statement: {}", id);
        String query = pm.get("galleryItem.getById");
        PreparedStatement ps;
        GalleryItem item = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = new GalleryItem();
                item.setId(id);
                item.setFullImagePath(rs.getString("fullimage_path"));
                item.setThumbPath(rs.getString("thumb_path"));
                item.setMainImage(rs.getInt("is_main") == 1);
                item.setGalleryId(rs.getLong("gallery_id"));
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return item;
    }

    @Override
    public List<GalleryItem> getAll() {
        log.trace("Gallery item getAll statement not implementing");
        return null;
    }

    @Override
    public boolean delete(GalleryItem entity) {
        log.trace("Gallery item delete statement: {}", entity);
        String query = pm.get("galleryItem.delete");
        return JdbcCommonDictDao.delete(query, connection, entity);
    }

    @Override
    public List<GalleryItem> getAllForGallery(Long id) {
        log.trace("getById statement: {}", id);
        String query = pm.get("galleryItem.getAllForGallery");
        PreparedStatement ps;
        GalleryItem item = null;
        List<GalleryItem> itemList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = new GalleryItem();
                item.setId(rs.getLong("id"));
                item.setFullImagePath(rs.getString("fullimage_path"));
                item.setThumbPath(rs.getString("thumb_path"));
                item.setMainImage(rs.getInt("is_main") == 1);
                item.setGalleryId(rs.getLong("gallery_id"));
                itemList.add(item);
            }
        } catch (SQLException e) {
            log.error("getById: {}", id);
            throw new JdbcDaoException("Unable to query SQL", e);
        }
        return itemList;
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
