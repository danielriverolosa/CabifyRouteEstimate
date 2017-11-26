package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Estimate implements Serializable {

    private Vehicle vehicle;
    private int totalPrice;
    private String priceFormatted;

    private Estimate(Builder builder) {
        setVehicle(builder.vehicle);
        setTotalPrice(builder.totalPrice);
        setPriceFormatted(builder.priceFormatted);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

    public void setPriceFormatted(String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }


    public static final class Builder {
        private Vehicle vehicle;
        private int totalPrice;
        private String priceFormatted;

        public Builder() {
        }

        public Builder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder totalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder priceFormatted(String priceFormatted) {
            this.priceFormatted = priceFormatted;
            return this;
        }

        public Estimate build() {
            return new Estimate(this);
        }
    }
}
