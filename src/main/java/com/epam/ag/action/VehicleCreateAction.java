package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
import com.epam.ag.dao.DaoFactory;
import com.epam.ag.model.*;
import com.epam.ag.service.GalleryService;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.DictionaryLoader;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class VehicleCreateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VehicleCreateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // TODO как правильно поступить с формой, чтобы после поста выбранные данные остались

        DictionaryLoader.loadDictionaries(req);
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        // IF POST
        if (req.getMethod().equalsIgnoreCase("POST")) {
            DaoFactory daoFactory = DaoFactory.getInstance();

            // Run validation
            FormValidator validator = new FormValidator();
            SystemMessage systemMessage = validator.validateForm(req);
            if (systemMessage.hasErrors()) {
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }

            /*
             1 VALIDATE POST
             2 If has gallery - save gallery
             3 Set post to model
             3 Save model
             4 Add BLOB to gallery
             */

            // If new image selected trying ti upload it
            Gallery gallery = new Gallery("G" + req.getParameter("model"));
            try {
                if (req.getPart("gallery").getSize() > 0) {
                    // New image has been selected
                    log.trace("New gallery found");
                    GalleryService galleryService = new GalleryService();
                    gallery = galleryService.save(gallery, false);
                    GalleryItem item = galleryService.createBLOBItem(req, "gallery", gallery.getId(), true);
                    gallery.addImage(item);
                }
            } catch (IOException | ServletException e) {
                systemMessage.addError("form.vehicle.gallery", "gallery.image.not_uploaded");
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }

            Vehicle vehicle = new Vehicle();
            vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);
            vehicle.setGallery(gallery);

            try {
                // Trying to save
                VehicleService vs = new VehicleService();
                vehicle = vs.save(vehicle);
            } catch (Exception e) {
                // Error occurred
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }
            req.getSession().setAttribute("systemMessage", new SystemMessage("form.save.success", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-list";
        }

        // Тестовые ошибки
//        SystemMessage msg = new SystemMessage("form.save.error", SystemMessage.ERROR);
//        msg.addError("user-name", "username.wrong-length");
//        msg.addError("user-passwd", "username.pass.too-short");     // Probably error here. Only one record will be displayed
//        msg.addError("user-passwd", "username.pass.empty");
//        msg.addError("user-date", "username.date.wrong-date-format");
//        req.setAttribute("systemMessage", msg);

        return "admin/vehicle-form";
    }
}
