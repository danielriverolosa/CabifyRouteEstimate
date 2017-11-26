package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Placement implements Serializable {

    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private Contact contact;

    private Placement(Builder builder) {
        setLatitude(builder.latitude);
        setLongitude(builder.longitude);
        setStreet(builder.street);
        setCity(builder.city);
        setContact(builder.contact);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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


    public static final class Builder {
        private double latitude;
        private double longitude;
        private String street;
        private String city;
        private Contact contact;

        public Builder() {
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Placement build() {
            return new Placement(this);
        }
    }
}
