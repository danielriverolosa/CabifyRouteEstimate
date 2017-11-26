package com.rivero.daniel.cabifyestimate.presentation.route.presenter;


import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.rivero.daniel.cabifyestimate.data.service.LocationServiceImpl;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.domain.service.LocationService;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.BasePresenter;
import com.rivero.daniel.cabifyestimate.presentation.common.utils.ConstantUtils;
import com.rivero.daniel.cabifyestimate.presentation.route.view.RouteSelectorView;

import javax.inject.Inject;

import timber.log.Timber;

@ViewScope
public class RouteSelectorPresenterImpl extends BasePresenter<RouteSelectorView> implements RouteSelectorPresenter, LocationServiceImpl.LocationServiceListener {

    private static final int ORIGIN_TAG =  111;
    private static final int DESTINY_TAG =  112;

    private LocationService locationService;

    @Inject
    RouteSelectorPresenterImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void onCreate(RouteSelectorView view) {
        super.onCreate(view);
        locationService.init();
        locationService.setLocationServiceListener(this);
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
        getView().setMarker(place.getLatLng(), ORIGIN_TAG);
    }

    @Override
    public void updateDestinyPlace(Place place) {
        Placement placement = getPlacement(place);
        getView().updateDestiny(placement);
        getView().setMarker(place.getLatLng(), DESTINY_TAG);
    }

    @NonNull
    private Placement getPlacement(Place place) {
        Double latitude = place.getLatLng().latitude;
        Double longitude = place.getLatLng().longitude;

        String city = locationService.getCityNameByCoordinates(latitude, longitude);

        return new Placement.Builder()
                .latitude(latitude)
                .longitude(longitude)
                .street((String) place.getName())
                .city(city)
                .build();
    }

    @Override
    public void onClickShowPlaceSelectorDestiny() {
        getView().showDestiny();
        getView().checkDisableButton();
    }

    @Override
    public void onClickContinue(Placement origin, Placement destiny) {
        Route route = new Route.Builder()
                .originPlacement(origin)
                .destinyPlacement(destiny)
                .build();

        navigator.showEstimateList(route);
    }

    @Override
    public void onStartLocationService() {
        locationService.onStart();
    }

    @Override
    public void onStopLocationService() {
        locationService.onStop();
    }

    @Override
    public void requestLocation() {
        locationService.requestLocationUpdate();
    }

    @Override
    public void onLocation(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        Placement placement = new Placement.Builder()
                .latitude(latitude)
                .longitude(longitude)
                .street(locationService.getStreetByCoordinates(latitude, longitude))
                .city(locationService.getCityNameByCoordinates(latitude, longitude))
                .build();

        getView().updateOrigin(placement);
        setMarkerToMap(latitude, longitude);
        getView().checkDisableButton();
    }

    private void setMarkerToMap(Double latitude, Double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        getView().setMarker(latLng, ConstantUtils.PLACE_AUTOCOMPLETE_ORIGIN_REQUEST_CODE);
    }

    @Override
    public void onLocationNotFound() {
        Timber.e("Location not found");
        getView().showMessage("Location not found");
    }
}
