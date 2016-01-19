package com.epam.ag.validator;

/**
 * Check value with regex
 *
 * @author Govorov Andrey
 */
public class RegexValidator extends BaseValidator implements Validator {

    private String regex;

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String param) {
        if (param == null) return false;
        return param.matches(regex);
    }
}
