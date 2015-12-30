package com.epam.ag.validator;

/**
 * @author Govorov Andrey.
 */
public class MonthValidator extends BaseValidator implements Validator {
    @Override
    public boolean isValid(String param) {
        int month;
        try {
            month = Integer.parseInt(param);
        } catch (Exception e) {
            return false;
        }

        return (month >= 1 && month <= 12);
    }
}
