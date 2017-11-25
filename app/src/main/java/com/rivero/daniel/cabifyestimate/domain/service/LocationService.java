package com.rivero.daniel.cabifyestimate.domain.service;


import static com.rivero.daniel.cabifyestimate.data.service.LocationServiceImpl.LocationServiceListener;

public interface LocationService {

    void onStart();

    void onStop();

    void requestLocationUpdate();

    void setLocationServiceListener(LocationServiceListener locationServiceListener);

    void requestLocationPermission();

    void init();

    String getCityNameByCoordinates(double lat, double lon);

    String getStreetByCoordinates(double lat, double lon);
}
