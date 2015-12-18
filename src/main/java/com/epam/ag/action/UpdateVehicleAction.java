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
 * @author by Govorov Andrey.
 */
public class UpdateVehicleAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdateVehicleAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DictionaryLoader.loadDictionaries(req);
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        Long vehicleId;
        try {
            vehicleId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.setAttribute("systemMessage", new SystemMessage("vehicle.form.wrong.id", SystemMessage.ERROR));
            return "admin/vehicle-form";
        }

        // Get data for showing
        VehicleService service = new VehicleService();
        Vehicle vehicle = service.getVehicle(vehicleId);
        if (vehicle == null) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.form.no.data", SystemMessage.ERROR));
            return "redirect:controller?action=vehicle-list";
        }

        // Validation
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("vehicle", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-form";
        }

        vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);

        // Create gallery if uploaded
        boolean isImageUpload = false;
        try {
            if (req.getPart("gallery").getSize() > 0) {
                isImageUpload = true;
            }
        } catch (IOException | ServletException e) {
            systemMessage.addError("form.vehicle.gallery", "gallery.image.not_uploaded");
            req.setAttribute("systemMessage", systemMessage);
            return "admin/vehicle-form";
        }

        // TODO При обновлении изображения, постоянно создается новая запись, старое изображение теряется... Надо фиксить
        if (isImageUpload) {
            // New image has been selected
            log.trace("New gallery found");
            Gallery gallery = new Gallery("G" + req.getParameter("model"));
            GalleryService galleryService = new GalleryService();
            gallery = galleryService.save(gallery, false);
            GalleryItem item = galleryService.createBLOBItem(req, "gallery", gallery.getId(), true);
            gallery.addImage(item);
            vehicle.setGallery(gallery);
        }

        // Save model
        boolean isSaved = service.updateVehicle(vehicle);
        if (!isSaved) {
            req.setAttribute("systemMessage", new SystemMessage("vehicle.save.fail", SystemMessage.ERROR));
            return "admin/vehicle-form";
        }

        req.getSession().setAttribute("systemMessage", new SystemMessage("vehicle.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=vehicle-update&id=" + vehicleId;
    }
}
