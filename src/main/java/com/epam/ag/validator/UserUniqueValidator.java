package com.epam.ag.validator;

import com.epam.ag.model.User;
import com.epam.ag.service.UserService;

/**
 * Validator special for User model
 *
 * @author Govorov Andrey
 */
public class UserUniqueValidator extends BaseValidator implements Validator {

    @Override
    public boolean isValid(String param) {
        UserService service = new UserService();
        User user = service.getUserByEmail(param);
        return user == null;
    }
}
