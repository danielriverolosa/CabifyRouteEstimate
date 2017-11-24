package com.rivero.daniel.cabifyestimate.domain;


import java.io.Serializable;
import java.util.Date;

public class Route implements Serializable {

    private Placement startPlacement;
    private Placement endPlacement;
    private Date startAt;

    public Placement getStartPlacement() {
        return startPlacement;
    }

    public void setStartPlacement(Placement startPlacement) {
        this.startPlacement = startPlacement;
    }

    public Placement getEndPlacement() {
        return endPlacement;
    }

    public void setEndPlacement(Placement endPlacement) {
        this.endPlacement = endPlacement;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }
}
