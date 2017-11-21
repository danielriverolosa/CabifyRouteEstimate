package com.rivero.daniel.cabifyroutestimate.domain;


import java.io.Serializable;
import java.util.Date;

public class Route implements Serializable {

    private Place startPlace;
    private Place endPlace;
    private Date startAt;

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(Place endPlace) {
        this.endPlace = endPlace;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }
}
