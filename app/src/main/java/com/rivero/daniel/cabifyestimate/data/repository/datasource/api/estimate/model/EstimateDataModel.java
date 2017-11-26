package com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model;


import com.google.gson.annotations.SerializedName;

public class EstimateDataModel {

    @SerializedName("vehicle_type")
    private VehicleDataModel vehicle;
    @SerializedName("total_price")
    private int totalPrice;
    @SerializedName("price_formatted")
    private String priceFormatted;

    public VehicleDataModel getVehicle() {
        return vehicle;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

}
