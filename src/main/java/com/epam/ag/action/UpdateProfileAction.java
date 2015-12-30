package com.epam.ag.action;

import com.epam.ag.model.User;
import com.epam.ag.model.user.Address;
import com.epam.ag.model.user.Passport;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.DateConverter;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Govorov Andrey.
 */
public class UpdateProfileAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {


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
        return null;
    }
}
