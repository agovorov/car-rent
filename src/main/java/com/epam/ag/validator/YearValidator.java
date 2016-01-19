package com.epam.ag.validator;

/**
 * Validate value as year.
 * Limitation: year should be more than 1900 and les than 2050
 *
 * @author Govorov Andrey.
 */
public class YearValidator extends BaseValidator implements Validator {
    @Override
    public boolean isValid(String param) {
        int year;
        try {
            year = Integer.parseInt(param);
        } catch (Exception e) {
            return false;
        }

        return (year >= 1900 && year <= 2050);
    }
}
