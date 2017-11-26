package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;

public class Vehicle implements Serializable {

    private String name;
    private String shortName;
    private String description;
    private String urlIcon;

    private Vehicle(Builder builder) {
        setName(builder.name);
        setShortName(builder.shortName);
        setDescription(builder.description);
        setUrlIcon(builder.urlIcon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }


    public static final class Builder {
        private String name;
        private String shortName;
        private String description;
        private String urlIcon;

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

        public Builder urlIcon(String urlIcon) {
            this.urlIcon = urlIcon;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
