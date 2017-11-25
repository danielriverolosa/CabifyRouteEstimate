package com.rivero.daniel.cabifyestimate.presentation.route.view;


import com.google.android.gms.maps.model.LatLng;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseView;

public interface RouteSelectorView extends BaseView {

    void setMarker(LatLng latLng, int tag);

    void updateOrigin(Placement placement);

    void updateDestiny(Placement placement);

    void showDestiny();

    void checkDisableButton();

}
