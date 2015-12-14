package com.epam.ag.validator;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface Validator {

    void setMessage(String msg);
    String getMessage();
    boolean isValid(String param);
}
