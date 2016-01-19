package com.epam.ag.validator;

/**
 * @author Govorov Andrey
 */
public interface Validator {

    String getMessage();

    void setMessage(String msg);

    boolean isValid(String param);
}
