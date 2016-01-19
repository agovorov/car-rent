package com.epam.ag.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Check if value is double value
 *
 * @author Govorov Andrey.
 */
public class DoubleValidator extends BaseValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(FormValidator.class);

    @Override
    public boolean isValid(String param) {
        // Пробуем привести к Double
        try {
            double d = Double.parseDouble(param);
        } catch (Exception e) {
            log.trace("Wrong format");
            return false;
        }
        log.trace("Ok double");
        return true;
    }
}
