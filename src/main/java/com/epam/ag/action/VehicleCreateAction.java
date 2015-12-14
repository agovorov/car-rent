package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.GalleryDao;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.*;
import com.epam.ag.propmanager.PropertiesManager;
import com.epam.ag.service.GalleryService;
import com.epam.ag.service.VehicleService;
import com.epam.ag.utils.DictionaryLoader;
import com.epam.ag.utils.ImageUploader;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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

            //req.setAttribute("year", 2015);

            // If form has error show them
            if (systemMessage.hasErrors()) {
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }

//            Vehicle vehicle = new Vehicle();
//            vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);
//            try {
//                final String path = PropertiesManager.getInstance().get("config.properties", "upload_img_dir");
//                final Part filePart = req.getPart("f-gallery");
//
//                // Uploading image
//                ImageUploader imageUploader = new ImageUploader();
//                String filename = imageUploader.fromRequest(filePart, path);
//                log.trace("Successfully uploaded to {}", filename);
//
//                // save image to db
//                Gallery gallery = new Gallery(vehicle.getModel());
//                gallery.addImage(new GalleryItem(filename, filename, true));
//                vehicle.setGallery(gallery);
//            } catch (IOException | ServletException e) {
//                // Unable to upload image
//                log.error("Unable to upload image", e);
//                systemMessage.setType(SystemMessage.ERROR);
//                systemMessage.addError("f-gallery", "form.save.gallery.error");
//            }
//
//            try {
//                // Trying to save
//                VehicleService vs = new VehicleService();
//                vehicle = vs.save(vehicle);
//            } catch (Exception e) {
//                // Error occurred
//                req.setAttribute("systemMessage", systemMessage);
//                return "admin/vehicle-form";
//            }
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
