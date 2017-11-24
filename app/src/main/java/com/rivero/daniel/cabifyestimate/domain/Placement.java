package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Placement implements Serializable {

    private String latitude;
    private String longitude;
    private String street;
    private String city;
    private Contact contact;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String[] getLocation() {
        return new String[]{latitude, longitude};
    }

}
