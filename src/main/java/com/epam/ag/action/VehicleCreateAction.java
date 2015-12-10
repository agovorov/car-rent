package com.epam.ag.action;

import com.epam.ag.action.helpers.ModelLoader;
import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.VehicleDao;
import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.Gallery;
import com.epam.ag.model.Vehicle;
import com.epam.ag.propmanager.PropertiesManager;
import com.epam.ag.utils.DictionaryLoader;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class VehicleCreateAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(VehicleCreateAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String localeString = String.valueOf(req.getAttribute("locale"));
        log.trace("Read locale from req: {}", localeString);

        DaoFactory daoFactory = DaoFactory.getInstance();
        if (localeString != null) {
            daoFactory.setLocale(new java.util.Locale(localeString));
        }
        DictionaryLoader.loadDictionaries(req);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);

        Vehicle vehicle = new Vehicle();
        // Class for sending messages
        SystemMessage systemMessage = new SystemMessage();

        // IF POST
        if (req.getMethod().equalsIgnoreCase("POST")) {
            vehicle = ModelLoader.loadVehicleFromRequest(req, vehicle);

            // TODO Загрузка изображения
            // http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
            final String path = PropertiesManager.getInstance().get("config.properties", "upload_img_dir");
            OutputStream out = null;
            InputStream filecontent = null;
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
                final Part filePart = req.getPart("f-gallery");
                final String fileName = getFileName(filePart);


                out = new FileOutputStream(new File(path + File.separator + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                log.trace("File{} being uploaded to {}", fileName, path);
                vehicle.setGallery(new Gallery(fileName));
            } catch (IOException | ServletException e) {
                systemMessage.setType(SystemMessage.ERROR);
                systemMessage.addError("f-gallery", "form.save.gallery.error");
                log.warn("Unable to upload gallery", e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (filecontent != null) {
                    try {
                        filecontent.close();
                    } catch (IOException e) {
                    }
                }
                if (writer != null) {
                    writer.close();
                }
            }

            //part.getInputStream()
            // в бд сохраняешь относительный путь
            // ну можешь ради интереса и бд попробовать положить
            // там как раз в prepStatement в блоб нужно совать инпутСтрим ps.setBlob(1, inputStream);

            // Trying to save
            VehicleDao daoVehicle = daoFactory.getDao(VehicleDao.class);
            try {
                vehicle = daoVehicle.save(vehicle);
            } catch (Exception e) {
                // Error occurred
                req.setAttribute("systemMessage", systemMessage);
                return "admin/vehicle-form";
            }
            req.getSession().setAttribute("systemMessage", new SystemMessage("form.save.success", SystemMessage.SUCCESS));
            return "redirect:controller?action=vehicle-list";
        }


        // Тестовые ошибки
        SystemMessage msg = new SystemMessage("form.save.error", SystemMessage.ERROR);
        msg.addError("user-name", "username.wrong-length");
        msg.addError("user-passwd", "username.pass.too-short");
        msg.addError("user-date", "username.date.wrong-date-format");
        req.setAttribute("systemMessage", msg);

        return "admin/vehicle-form";
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        log.trace("Part Header = {}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
