package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Place implements Serializable {

    private String latitude;
    private String longitude;
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
