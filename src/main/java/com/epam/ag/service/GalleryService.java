package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.GalleryDao;
import com.epam.ag.dao.GalleryItemDao;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.GalleryItem;
import com.epam.ag.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class GalleryService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(GalleryService.class);

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    // Транзакция
    // Save gallery
    // save galleryItem
    // save
    public Gallery save(Gallery gallery, boolean transaction) {
        GalleryDao dao = daoFactory.getDao(GalleryDao.class);

        if (transaction) {
            dao.beginTransaction();
        }
        gallery = dao.save(gallery);

        int itemsCount = gallery.size();
        if (itemsCount > 0) {
            Long galleryId = gallery.getId();
            // To every gallery item set gallery id
            GalleryItemDao itemDao = daoFactory.getDao(GalleryItemDao.class);
            GalleryItem item = null;
            for (int i = 0; i < gallery.size(); i++) {
                item = gallery.getItem(i);
                item.setGalleryId(galleryId);
                item = itemDao.save(item);
            }
        }
        if (transaction) {
            dao.commit();
        }
        return gallery;
    }

    public Gallery get(Long id, Gallery gallery) {
        // Load data for gallery entity
        GalleryDao dao = daoFactory.getDao(GalleryDao.class);

        gallery = dao.getById(id);

        // Loading items
        GalleryItemDao itemDao = daoFactory.getDao(GalleryItemDao.class);
        GalleryItem item = null;
        List<GalleryItem> items = itemDao.getAllForGallery(id);
        gallery.addItems(items);

        return gallery;
    }

    /**
     * Removing entity from DB and from list
     *
     * @param idx     Counting from 0 position
     * @param gallery
     * @return
     */
    public Gallery removeItem(int idx, Gallery gallery) {
        GalleryItem item = gallery.getItem(idx);

        // Удаляем запись из базы
        GalleryItemDao itemDao = daoFactory.getDao(GalleryItemDao.class);
        boolean delete = itemDao.delete(item);
        if (delete) {
            gallery.removeImage(idx);
        }
        return gallery;
    }

    /**
     * Add BLOB object to database.
     * @param req Servlet request to get files`s Part
     * @param partName Name of the Part in servlet request
     * @param galleryId Gallery ID
     * @param isMainPage Main image or not
     * @return Item of gallery
     */
    public GalleryItem createBLOBItem(HttpServletRequest req, String partName, Long galleryId, boolean isMainPage) {
        GalleryItem item = new GalleryItem();
        item.setGalleryId(galleryId);
        item.setMainImage(isMainPage);

        GalleryItemDao itemDao = daoFactory.getDao(GalleryItemDao.class);
        InputStream imageContent = null;
        try {
            final String path = PropertiesManager.getInstance().get("config.properties", "upload_img_dir");
            log.trace("IMG path: {}", path);
            final Part filePart = req.getPart(partName);
            imageContent = filePart.getInputStream();
            item = itemDao.saveBLOB(item, imageContent);
        } catch (IOException | ServletException e) {
            log.error("Unable to upload gallery", e);
            throw new RuntimeException("Unable to upload resource");
        } finally {
            if (imageContent != null) try {
                imageContent.close();
            } catch (IOException e) {
                log.warn("Unable to close resource {}", e);
            }
        }
        return item;
    }
}
