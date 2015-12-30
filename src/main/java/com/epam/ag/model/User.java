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
    private IDDocument passport;
    private UserRole role;

    private String phone;

    public User(long userId) {
        super(userId);
    }

    public IDDocument getPassport() {
        return passport;
    }

    public void setPassport(IDDocument passport) {
        this.passport = passport;
    }

    public User() {
        this.role = new UserRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return role.getId();
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }

    public UserRole getRole() {
        return role;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", passport=" + passport +
                ", role=" + role +
                '}';
    }
}
