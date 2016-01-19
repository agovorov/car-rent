package com.epam.ag.validator;

/**
 * Check length of value
 *
 * @author Govorov Andrey
 */
public class LengthValidator extends BaseValidator implements Validator {

    private Integer min;
    private Integer max;

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(String param) {
        if (param == null) return false;

        if (min == null && max != null) {
            return param.length() <= max;
        }

        if (min != null && max == null) {
            return param.length() >= min;
        }

        return param.length() >= min && param.length() <= max;
    }
}
