package com.epam.ag.validator;

/**
 * @author Govorov Andrey.
 */
public class YearValidator  extends BaseValidator implements Validator {
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
