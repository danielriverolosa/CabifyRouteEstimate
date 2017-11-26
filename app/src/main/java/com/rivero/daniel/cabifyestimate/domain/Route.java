package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;
import java.util.Date;

public class Route implements Serializable {

    private Placement originPlacement;
    private Placement destinyPlacement;
    private Date startAt;

    private Route(Builder builder) {
        setOriginPlacement(builder.originPlacement);
        setDestinyPlacement(builder.destinyPlacement);
        setStartAt(builder.startAt);
    }

    public Placement getOriginPlacement() {
        return originPlacement;
    }

    public void setOriginPlacement(Placement originPlacement) {
        this.originPlacement = originPlacement;
    }

    public Placement getDestinyPlacement() {
        return destinyPlacement;
    }

    public void setDestinyPlacement(Placement destinyPlacement) {
        this.destinyPlacement = destinyPlacement;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }


    public static final class Builder {
        private Placement originPlacement;
        private Placement destinyPlacement;
        private Date startAt;

        public Builder() {
        }

        public Builder originPlacement(Placement originPlacement) {
            this.originPlacement = originPlacement;
            return this;
        }

        public Builder destinyPlacement(Placement destinyPlacement) {
            this.destinyPlacement = destinyPlacement;
            return this;
        }

        public Builder startAt(Date startAt) {
            this.startAt = startAt;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
