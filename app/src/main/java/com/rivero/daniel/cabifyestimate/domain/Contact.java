package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String mobileCC;
    private String mobileNumber;

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
}
