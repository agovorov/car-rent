package com.epam.ag.model.user;

import com.epam.ag.model.BaseEntity;

/**
 * @author Govorov Andrey
 */
public class Address extends BaseEntity {
    private String country;
    private String city;
    private String street;
    private int appartmentNumber;
    private String streetNumber;

    public Address() {

    }

    public Address(long id) {
        super(id);
    }

    public int getAppartmentNumber() {
        return appartmentNumber;
    }

    public void setAppartmentNumber(int appartmentNumber) {
        this.appartmentNumber = appartmentNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", appartmentNumber=" + appartmentNumber +
                ", streetNumber='" + streetNumber + '\'' +
                '}';
    }
}
