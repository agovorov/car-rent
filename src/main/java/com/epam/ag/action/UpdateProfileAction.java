package com.epam.ag.action;

import com.epam.ag.model.User;
import com.epam.ag.model.user.Address;
import com.epam.ag.model.user.IDDocument;
import com.epam.ag.model.user.Passport;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.DateConverter;
import com.epam.ag.utils.SystemMessage;
import com.epam.ag.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Govorov Andrey.
 */
public class UpdateProfileAction extends UserAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdateProfileAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!getUser(req)) {
            return "redirect:controller?action=login";
        }

        Map<String, String> validatorRules = new LinkedHashMap<>();
        // Personal users
        validatorRules.put("lastname", "user.lastname");
        validatorRules.put("firstname", "user.firstname");
        validatorRules.put("phone", "user.phone");

        // Address rules
        validatorRules.put("adr_country", "confirm.adr_country");
        validatorRules.put("adr_city", "confirm.adr_city");
        validatorRules.put("adr_street", "confirm.adr_street");
        validatorRules.put("adr_street_number", "confirm.adr_street_number");
        validatorRules.put("adr_flat", "confirm.adr_flat");

        // Document`s rules
        validatorRules.put("pass_series", "confirm.pass_series");
        validatorRules.put("pass_number", "confirm.pass_number");
        validatorRules.put("pass_issue", "confirm.pass_issue");
        validatorRules.put("pass_expire", "confirm.pass_expire");
        validatorRules.put("pass_place", "confirm.pass_place");

        // Address
        FormValidator validator = new FormValidator();
        SystemMessage systemMessage = validator.validateForm(validatorRules, req);
        if (systemMessage.hasErrors()) {
            log.trace("Validator fail");
            req.setAttribute("systemMessage", systemMessage);
            return "client/profile";
        }

        User user = (User) req.getSession(false).getAttribute("user");
        if (user == null) {
            log.trace("User is null");
            req.getSession().setAttribute("systemMessage", new SystemMessage("profile.user.not.set", SystemMessage.ERROR));
            return "redirect:controller?action=profile";
        }

        Date issuePassportDate = DateConverter.strToDate(req.getParameter("pass_issue"));
        Date expirePassportDate = DateConverter.strToDate(req.getParameter("pass_expire"));

        UserService service = new UserService();
        service.loadPassportData(user);

        // User info
        user.setLastName(req.getParameter("lastname"));
        user.setFirstName(req.getParameter("firstname"));
        user.setPhone(req.getParameter("phone"));

        Passport passport = (Passport) user.getPassport();
        if (passport == null) {
            passport = new Passport();
            user.setPassport(passport);
        }
        passport.setDocumentNumber(req.getParameter("pass_number"));
        passport.setExpirationDate(expirePassportDate);
        passport.setIssueDate(issuePassportDate);
        passport.setIssuePlace(req.getParameter("pass_place"));
        passport.setSeries(req.getParameter("pass_series"));

        // Add address
        Address livingAddress = user.getPassport().getLivingAddress();
        if (livingAddress == null) {
            livingAddress = new Address();
        }
        int flatNumber = Integer.parseInt(req.getParameter("adr_flat"));

        livingAddress.setCountry(req.getParameter("adr_country"));
        livingAddress.setCity(req.getParameter("adr_city"));
        livingAddress.setStreet(req.getParameter("adr_street"));
        livingAddress.setStreetNumber(req.getParameter("adr_street_number"));
        livingAddress.setAppartmentNumber(flatNumber);
        log.trace("Address: {}", livingAddress);

        passport.setAddress(livingAddress);

        boolean isAdded = service.saveUser(user);
        if (!isAdded) {
            log.trace("Unable to save user via UserService");
            req.getSession().setAttribute("systemMessage", new SystemMessage("profile.user.save.error", SystemMessage.ERROR));
            return "redirect:controller?action=profile";
        }

        log.trace("Profile successfully saved");
        req.getSession().setAttribute("systemMessage", new SystemMessage("profile.save.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=profile";
    }
}
