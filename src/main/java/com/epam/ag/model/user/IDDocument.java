package com.epam.ag.model.user;

import com.epam.ag.model.User;

import java.util.Date;

/**
 * @author Govorov Andrey
 */
public abstract class IDDocument {
    private User owner;
    private int number;
    private Date dateOfIssue;
    private Date expirationDate;
    private String placeOfIssue;
    private Address livingAddress;
}
