package com.epam.ag.validator;

/**
 * @author Govorov Andrey
 */
public class NotNullValidator extends BaseValidator implements Validator {

    @Override
    public boolean isValid(String param) {
        return param != null;
    }
}
