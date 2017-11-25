package com.rivero.daniel.cabifyestimate.presentation.route.presenter;


import com.google.android.gms.location.places.Place;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.presentation.base.Presenter;
import com.rivero.daniel.cabifyestimate.presentation.route.view.RouteSelectorView;

public interface RouteSelectorPresenter extends Presenter<RouteSelectorView> {

    void updateOriginPlace(Place place);

    void updateDestinyPlace(Place place);

    void onClickShowPlaceSelectorDestiny();

    void onClickContinue(Placement origin, Placement destiny);

    void onStartLocationService();

    void onStopLocationService();

    void requestLocation();
}
