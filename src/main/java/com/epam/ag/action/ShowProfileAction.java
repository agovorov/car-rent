package com.epam.ag.action;

import com.epam.ag.model.Breadcrumbs;
import com.epam.ag.model.BreadcrumbsItem;
import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ShowProfileAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowProfileAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkUser(req, UserRole.CLIENT)) {
            log.warn("WRONG user role");
            return "redirect:controller?action=login";
        }

        log.trace("user: {}", user);
        req.setAttribute("user", user);

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        List<BreadcrumbsItem> breadcrumbItems = breadcrumbs.getItems(getClass().getSimpleName());
        req.setAttribute("breadcrumbItems", breadcrumbItems);
        return "client/profile";
    }
}
