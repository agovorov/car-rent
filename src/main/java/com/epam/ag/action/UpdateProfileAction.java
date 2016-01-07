package com.epam.ag.action;

import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Govorov Andrey.
 */
public class UpdateProfileAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdateProfileAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkUser(req, UserRole.CLIENT)) {
            log.warn("WRONG user role");
            return "redirect:controller?action=login";
        }

        log.trace("user: {}", user);
        req.setAttribute("user", user);
        return "client/profile";

//        Map<String, String> validatorRules = new LinkedHashMap<>();
//        // Personal users
//        validatorRules.put("lastname", "user.lastname");
//        validatorRules.put("firstname", "user.firstname");
//        validatorRules.put("email", "user.email");
//        validatorRules.put("phone", "user.phone");
//
//        // Address rules
//        validatorRules.put("adr_country", "confirm.adr_country");
//        validatorRules.put("adr_city", "confirm.adr_city");
//        validatorRules.put("adr_street", "confirm.adr_street");
//        validatorRules.put("adr_street_number", "confirm.adr_street_number");
//        validatorRules.put("adr_flat", "confirm.adr_flat");
//
//        // Document`s rules
//        validatorRules.put("pass_series", "confirm.pass_series");
//        validatorRules.put("pass_number", "confirm.pass_number");
//        validatorRules.put("pass_issue", "confirm.pass_issue");
//        validatorRules.put("pass_expire", "confirm.pass_expire");
//        validatorRules.put("pass_place", "confirm.pass_place");
//
//        // Address
//        FormValidator validator = new FormValidator();
//        SystemMessage systemMessage = validator.validateForm(validatorRules, req);
//        if (systemMessage.hasErrors()) {
//            log.trace("Validator fail");
//            req.setAttribute("systemMessage", systemMessage);
//            return "client/profile";
//        }
        // Add address
//        int flatNumber = Integer.parseInt(req.getParameter("adr_flat"));
//        Address livingAddress = new Address();
//        livingAddress.setCountry(req.getParameter("adr_country"));
//        livingAddress.setCity(req.getParameter("adr_city"));
//        livingAddress.setStreet(req.getParameter("adr_street"));
//        livingAddress.setStreetNumber(req.getParameter("adr_street_number"));
//        livingAddress.setAppartmentNumber(flatNumber);
//        log.trace("Address: {}", livingAddress);
//
//        // Add passport
//        Date issuePassportDate = DateConverter.strToDate(req.getParameter("pass_issue"));
//        Date expirePassportDate = DateConverter.strToDate(req.getParameter("pass_expire"));
//        Passport passport = new Passport();
//        passport.setAddress(livingAddress);
//        passport.setDocumentNumber(req.getParameter("pass_number"));
//        passport.setExpirationDate(expirePassportDate);
//        passport.setIssueDate(issuePassportDate);
//        passport.setIssuePlace(req.getParameter("pass_place"));
//
//        // Add passport to user
//        User user = order.getCustomer();
//        user.setPassport(passport);
//
//        UserService service = new UserService();
//        boolean isAdded = service.saveUser(user);
//        if (!isAdded) {
//            log.trace("Unable to save user via UserService");
//            req.setAttribute("systemMessage", new SystemMessage("order.confirm.save.fail", SystemMessage.ERROR));
//            return "client/date_select";
//        }
//
//
//        log.trace("Profile successfully saved");
//        req.getSession().setAttribute("systemMessage", new SystemMessage("profile.save.success", SystemMessage.SUCCESS));
//        return "redirect:controller?action=profile";
    }
}
