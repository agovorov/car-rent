package com.epam.ag.servlet;

import com.epam.ag.model.Gallery;
import com.epam.ag.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Load image from database
 *
 * @author Govorov Andrey
 */
public class ImageServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ImageServlet.class);

    public void init() throws ServletException {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        GalleryService s = new GalleryService();
        Gallery gallery = s.getFullGallery(id);
        log.trace("Gallery loaded {}", gallery);
        byte[] bytes = s.getBLOB(gallery.getItem(0));
        resp.reset();
        resp.setContentType("image/png");
        resp.setContentLength(bytes.length);

        // Write image content to response.
        resp.getOutputStream().write(bytes);
    }
}
