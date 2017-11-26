package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String mobileCC;
    private String mobileNumber;

    private Contact(Builder builder) {
        setName(builder.name);
        setMobileCC(builder.mobileCC);
        setMobileNumber(builder.mobileNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileCC() {
        return mobileCC;
    }

    public void setMobileCC(String mobileCC) {
        this.mobileCC = mobileCC;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public static final class Builder {
        private String name;
        private String mobileCC;
        private String mobileNumber;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder mobileCC(String mobileCC) {
            this.mobileCC = mobileCC;
            return this;
        }

        public Builder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
