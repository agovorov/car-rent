package com.epam.ag.servlet;

import com.epam.ag.action.Action;
import com.epam.ag.action.ActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Govorov Andrey
 */
@MultipartConfig
@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = actionFactory.getAction(req);
        if (action == null) {
            log.error("Action not found! {}", req.getRequestURI());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String view = action.execute(req, resp);
        if (view == null) {
            log.error("View not found! {}", req.getRequestURI());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        log.trace("Controller -> action: {}", view);
        if (view.startsWith("redirect:")) {
            resp.sendRedirect(getRedirectLocation(view));
            return;
        }

        req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req, resp);
    }

    private String getRedirectLocation(String view) {
        return "/" + view.substring(9);
    }
}
