package com.epam.ag.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Is integer value. Zero and all negative numbers are false!
 *
 * @author Govorov Andrey
 */
public class PositiveIntegerValidator extends BaseValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(PositiveIntegerValidator.class);

    @Override
    public boolean isValid(String param) {
        // Пробуем привести к Int
        log.trace("StrToInt: {}", param);
        int i;
        try {
            i = Integer.parseInt(param);
            if (i < 1) {
                log.trace("Int < 1");
                return false;
            }
        } catch (Exception e) {
            log.trace("Wrong parse");
            return false;
        }

        log.trace("Int parse result {}", i);
        return true;
    }
}
