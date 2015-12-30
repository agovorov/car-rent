package com.epam.ag.action;

import com.epam.ag.model.User;
import com.epam.ag.model.user.UserRole;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.PasswordUtil;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey
 */
public class RegisterAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm("register", req);
        if (systemMessage.hasErrors()) {
            req.setAttribute("systemMessage", systemMessage);
            return "register";
        }

        String email = req.getParameter("email");
        String password = req.getParameter("passwd");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPhone(phone);
        user.setPassword(PasswordUtil.hashPassword(password));
        user.setRole(UserRole.CLIENT);

        UserService service = new UserService();
        boolean isSaved = service.saveUser(user);
        if (!isSaved) {
            log.info("Unable to register new user: {}", user);
            req.setAttribute("systemMessage", new SystemMessage("register.save.fail", SystemMessage.ERROR));
            return "register";
        }

        // It`s ok
        req.getSession().setAttribute("systemMessage", new SystemMessage("register.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=cabinet";
    }
}
