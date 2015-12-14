package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
import com.epam.ag.dao.*;
import com.epam.ag.model.*;
import com.epam.ag.model.dict.*;
import com.epam.ag.propmanager.PropertiesManager;
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
 * @author by Govorov Andrey.
 */
public class VehicleUpdateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VehicleUpdateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long vehicleId = Long.valueOf(req.getParameter("id"));

        DaoFactory daoFactory = DaoFactory.getInstance();

        // List of the manufactors
        DictionaryLoader.loadDictionaries(req);

        if (vehicleId <= 0) {
            req.setAttribute("systemMessage", new SystemMessage("Please, wrong ID parameter!", SystemMessage.ERROR));
            return "admin/vehicle-list";
        }
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        // Loading model
        VehicleDao dao = daoFactory.getDao(VehicleDao.class);
        Vehicle vehicle = new Vehicle();
        vehicle = dao.getById(vehicleId);

        if (vehicle == null) {
            req.setAttribute("systemMessage", new SystemMessage("Sorry, no data.", SystemMessage.ERROR));
            return "admin/vehicle-list";
        }

        if (req.getMethod().equals("POST")) {
            // Run validation
            FormValidator validator = new FormValidator();
            SystemMessage systemMessage = validator.validateForm(req);
            // If form has error show them
            //if (systemMessage.hasErrors()) {
            if (true) {
                req.setAttribute("systemMessage", systemMessage);
                req.setAttribute("vehicle", vehicle);
                return "admin/vehicle-form";
            }

            vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);

            try {
                final String path = PropertiesManager.getInstance().get("config.properties", "upload_img_dir");
                final Part filePart = req.getPart("f-gallery");

                // Uploading image
                ImageUploader imageUploader = new ImageUploader();
                String filename = imageUploader.fromRequest(filePart, path);
                log.trace("Successfully uploaded to {}", filename);

                // save image to db
                Gallery gallery = new Gallery(vehicle.getModel());
                gallery.addImage(new GalleryItem(filename, filename, true));
                vehicle.setGallery(gallery);
            } catch (IOException | ServletException e) {
                // Unable to upload image
                log.error("Unable to upload image", e);
                systemMessage.setType(SystemMessage.ERROR);
                systemMessage.addError("f-gallery", "form.save.gallery.error");
            }

            // Trying to save
            try {
                // Trying to save
                VehicleService vs = new VehicleService();
                vehicle = vs.save(vehicle);
            } catch (Exception e) {
                // Error occurred
                req.setAttribute("vehicle", vehicle);
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }
            req.getSession().setAttribute("systemMessage", new SystemMessage("Record successfully updated!", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-update&id=" + vehicle.getId();
        }

        req.setAttribute("vehicle", vehicle);
        return "admin/vehicle-form";
    }
}
