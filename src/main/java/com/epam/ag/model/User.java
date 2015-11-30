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

    public User() {
        this.role = new UserRole();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Long getRoleId() {
        return role.getId();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        // TODO Тут необходимо добавить функция шифрования пароля
        this.password = password;
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }
}
