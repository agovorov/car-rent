package com.epam.ag.validator;

/**
 * Check if value is integer value
 *
 * @author Govorov Andrey
 */
public class IntegerValidator extends BaseValidator implements Validator {
    @Override
    public boolean isValid(String param) {
        // Пробуем привести к Integer
        try {
            int i = Integer.parseInt(param);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
