package com.epam.ag.model;

import com.epam.ag.model.user.IDDocument;
import com.epam.ag.model.user.UserRole;

/**
 * @author Govorov Andrey
 */
public class User extends BaseEntity {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    // Ох и намучаюсь я с иерархией таблиц...
    private IDDocument passport;
    private UserRole role;
}
