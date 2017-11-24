package com.rivero.daniel.cabifyestimate.presentation.route.presenter;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import com.google.android.gms.location.places.Place;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.presentation.base.BasePresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.view.RouteSelectorView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class RouteSelectorPresenterImpl extends BasePresenter<RouteSelectorView> implements RouteSelectorPresenter {

    private Context context;

    @Inject
    RouteSelectorPresenterImpl(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().checkDisableButton();
    }

    @Override
    public void updateOriginPlace(Place place) {
        Placement placement = getPlacement(place);
        getView().updateOrigin(placement);
    }

    @Override
    public void updateDestinyPlace(Place place) {
        Placement placement = getPlacement(place);
        getView().updateDestiny(placement);
    }

    @NonNull
    private Placement getPlacement(Place place) {
        Placement placement = new Placement();
        placement.setStreet((String) place.getName());
        placement.setCity(getCityNameByCoordinates(place.getLatLng().latitude, place.getLatLng().longitude));
        return placement;
    }

    private String getCityNameByCoordinates(double lat, double lon) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null && addresses.size() > 0) {
                return addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            getView().showMessage(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public void onClickShowPlaceSelectorDestiny() {
        getView().showDestiny();
        getView().checkDisableButton();
    }

    @Override
    public void onClickContinue(Placement origin, Placement destiny) {

    }

}
