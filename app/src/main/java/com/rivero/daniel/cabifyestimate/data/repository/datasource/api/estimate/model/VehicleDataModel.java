package com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model;


import com.google.gson.annotations.SerializedName;

public class VehicleDataModel {
    @SerializedName("name")
    private String name;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("description")
    private String description;
    @SerializedName("icons")
    private Icon icon;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public Icon getIcon() {
        return icon;
    }

    public static class Icon {
        @SerializedName("regular")
        private String iconUrl;

        public String getIconUrl() {
            return iconUrl;
        }
    }
}
