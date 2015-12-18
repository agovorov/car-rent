package com.epam.ag.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @author Govorov Andrey
 */
@WebServlet(name = "ImageServlet", urlPatterns = "/i")
public class ImageServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);

}
