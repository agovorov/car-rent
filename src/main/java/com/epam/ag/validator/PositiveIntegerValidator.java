package com.epam.ag.validator;

/**
 * Is integer value. Zero and all negative numbers are false!
 *
 * @author Govorov Andrey
 */
public class PositiveIntegerValidator extends BaseValidator implements Validator {
    @Override
    public boolean isValid(String param) {
        // Пробуем привести к Int
        try {
            int i = Integer.parseInt(param);
            if (i < 1) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
