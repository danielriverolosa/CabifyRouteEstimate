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

    private VehicleDataModel(Builder builder) {
        name = builder.name;
        shortName = builder.shortName;
        description = builder.description;
        icon = builder.icon;
    }

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

    public static final class Builder {
        private String name;
        private String shortName;
        private String description;
        private Icon icon;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder icon(Icon icon) {
            this.icon = icon;
            return this;
        }

        public VehicleDataModel build() {
            return new VehicleDataModel(this);
        }
    }
}
