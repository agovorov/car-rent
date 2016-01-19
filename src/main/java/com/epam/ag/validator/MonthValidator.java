package com.epam.ag.validator;

/**
 * Check if month is correct. Should be from 1 to 12
 *
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
