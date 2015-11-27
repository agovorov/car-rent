package com.epam.ag.model.user;

import java.util.Date;

/**
 * @author Govorov Andrey
 */
public abstract class IDDocument {

    private int number;
    private Date dateOfIssue;
    private Date expirationDate;
    private String placeOfIssue;
    private Address livingAddress;
}
