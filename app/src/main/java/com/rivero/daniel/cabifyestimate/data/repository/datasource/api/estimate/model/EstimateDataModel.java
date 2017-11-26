package com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model;


import com.google.gson.annotations.SerializedName;

public class EstimateDataModel {

    @SerializedName("vehicle_type")
    private VehicleDataModel vehicle;
    @SerializedName("total_price")
    private int totalPrice;
    @SerializedName("price_formatted")
    private String priceFormatted;

    private EstimateDataModel(Builder builder) {
        vehicle = builder.vehicle;
        totalPrice = builder.totalPrice;
        priceFormatted = builder.priceFormatted;
    }

    public VehicleDataModel getVehicle() {
        return vehicle;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

    public static final class Builder {
        private VehicleDataModel vehicle;
        private int totalPrice;
        private String priceFormatted;

        public Builder() {
        }

        public Builder vehicle(VehicleDataModel vehicleDataModel) {
            vehicle = vehicleDataModel;
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

        public EstimateDataModel build() {
            return new EstimateDataModel(this);
        }
    }
}
