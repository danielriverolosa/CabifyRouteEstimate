package com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class EstimateRequest {

    @SerializedName("stops")
    List<StopLocation> stopLocationList = Arrays.asList(new StopLocation[2]);

    public void setOrigin(StopLocation stopLocation) {
        stopLocationList.set(0, stopLocation);
    }

    public void setDestiny(StopLocation stopLocation) {
        stopLocationList.set(1, stopLocation);
    }

    public static class StopLocation {
        @SerializedName("loc")
        List<Double> coordinates = Arrays.asList(new Double[2]);

        public void setLatitude(Double latitude) {
            coordinates.set(0, latitude);
        }

        public void setLongitude(Double longitude) {
            coordinates.set(1, longitude);
        }
    }

}
