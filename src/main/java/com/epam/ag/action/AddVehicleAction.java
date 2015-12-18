package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
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
public class AddVehicleAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(AddVehicleAction.class);

    // Надо почистить метод
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DictionaryLoader.loadDictionaries(req);
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("vehicle", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-form";
        }

        // Create vehicle
        Vehicle vehicle = new Vehicle();
        vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);

        // Create gallery
        Gallery gallery = new Gallery("G" + req.getParameter("model"));
        try {
            if (req.getPart("gallery").getSize() > 0) {
                // New image has been selected
                log.trace("New gallery found");
                GalleryService galleryService = new GalleryService();
                gallery = galleryService.save(gallery, false);
                GalleryItem item = galleryService.createBLOBItem(req, "gallery", gallery.getId(), true);
                gallery.addImage(item);
                vehicle.setGallery(gallery);
            }
        } catch (IOException | ServletException e) {
            systemMessage.addError("form.vehicle.gallery", "gallery.image.not_uploaded");
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-form";
        }

        // It`s ok save it and redirect to list
        VehicleService service = new VehicleService();
        boolean isAdded = service.addNewVehicle(vehicle);
        if (!isAdded) {
            req.setAttribute("systemMessage", new SystemMessage("vehicle.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-form";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-list";
    }
}
