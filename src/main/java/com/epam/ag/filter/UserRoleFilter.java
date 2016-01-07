package com.epam.ag.filter;

import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Govorov Andrey
 */
@WebFilter(filterName = "UserRoleFilter")
public class UserRoleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UserRoleFilter.class);
    private List<String> clientPage = new ArrayList<>();
    private List<String> adminPage = new ArrayList<>();
    private List<String> anonymousPage = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        // TODO Can be loaded from database later
        anonymousPage.add("index");
        anonymousPage.add("login");
        anonymousPage.add("rules");
        anonymousPage.add("contact");
        anonymousPage.add("register");
        anonymousPage.add("login");
        anonymousPage.add("order");
        anonymousPage.add("order-vehicle");
        anonymousPage.add("order-vehicle-filter");
        anonymousPage.add("order-verify");

        // TODO remove after debug
        anonymousPage.add("user-tmp");
        anonymousPage.add("admin-tmp");

        // Client`s rule
//        clientPage.add("index");
        clientPage.add("logout");
        clientPage.add("cabinet");
        clientPage.add("profile");
        clientPage.add("history");
        clientPage.add("orders");
        clientPage.add("order-detail");
        clientPage.add("order-pay");
        clientPage.add("order-refund");

        // Admin
        adminPage.add("index");
        adminPage.add("logout");

        adminPage.add("manufacturer-list");
        adminPage.add("manufacturer-create");
        adminPage.add("manufacturer-update");
        adminPage.add("manufacturer-delete");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        String actionName = httpRequest.getParameter("action");
        String actionKey = actionName;
        log.trace("Action key: {}", actionKey);

        // Looking for a user session data
        User user = (User) httpRequest.getSession().getAttribute("user");
        List currentUserList = getListsForUser(user);
        if (!currentUserList.contains(actionKey)) {
            log.trace("You shall not pass!");
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "role.not.allowed");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    private List getListsForUser(User user) {
        if (user == null) {
            log.trace("Anonymous found");
            return anonymousPage;
        }

        if (user.getRole().equals(UserRole.ADMIN)) {
            log.trace("Admin found");
            adminPage.addAll(anonymousPage);
            return adminPage;
        }

        if (user.getRole().equals(UserRole.CLIENT)) {
            log.trace("Client found");
            clientPage.addAll(anonymousPage);
            return clientPage;
        }

        log.warn("Role for user {} not found. Use anonymous.", user);
        return anonymousPage;
    }
}
