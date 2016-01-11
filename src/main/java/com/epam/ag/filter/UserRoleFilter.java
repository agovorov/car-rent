package com.epam.ag.filter;

import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class UserRoleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UserRoleFilter.class);
    private List<String> clientPage = new ArrayList<>();
    private List<String> adminPage = new ArrayList<>();
    private List<String> anonymousPage = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        // TODO Can be loaded from database later
        anonymousPage.add("index");
        anonymousPage.add("locale");
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
        anonymousPage.add("test");
        anonymousPage.add("user-tmp");
        anonymousPage.add("admin-tmp");

        // Client`s rule
        clientPage.add("logout");
        clientPage.add("cabinet");
        clientPage.add("profile");
        clientPage.add("history");
        clientPage.add("orders");
        clientPage.add("order-detail");
        clientPage.add("order-pay");
        clientPage.add("order-refund");

        // Admin
        adminPage.add("cabinet");
        adminPage.add("logout");
        adminPage.add("order-list");
        adminPage.add("order-detail-adm");
        adminPage.add("order-confirm");
        adminPage.add("order-confirm-detail");
        adminPage.add("order-issue");
        adminPage.add("order-received");

        adminPage.add("manufacturer-list");
        adminPage.add("manufacturer-create");
        adminPage.add("manufacturer-update");
        adminPage.add("manufacturer-delete");

        adminPage.add("color-list");
        adminPage.add("color-create");
        adminPage.add("color-update");
        adminPage.add("color-delete");

        adminPage.add("vehicle-type-list");
        adminPage.add("vehicle-type-create");
        adminPage.add("vehicle-type-update");
        adminPage.add("vehicle-type-delete");

        adminPage.add("vehicle-fuel-list");
        adminPage.add("vehicle-fuel-create");
        adminPage.add("vehicle-fuel-update");
        adminPage.add("vehicle-fuel-delete");

        adminPage.add("vehicle-gear-list");
        adminPage.add("vehicle-gear-create");
        adminPage.add("vehicle-gear-update");
        adminPage.add("vehicle-gear-delete");

        adminPage.add("vehicle-list");
        adminPage.add("vehicle-create");
        adminPage.add("vehicle-update");
        adminPage.add("vehicle-delete");

        adminPage.add("user-list");
        adminPage.add("user-create");
        adminPage.add("user-update");
        adminPage.add("user-delete");
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
