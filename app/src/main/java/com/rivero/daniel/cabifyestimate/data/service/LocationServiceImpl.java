package com.rivero.daniel.cabifyestimate.data.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.service.LocationService;
import com.rivero.daniel.cabifyestimate.domain.service.PermissionService;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ActivityContext;
import com.rivero.daniel.cabifyestimate.presentation.common.utils.ConstantUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.Provides;
import timber.log.Timber;


public class LocationServiceImpl implements LocationService, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @Inject
    PermissionService permissionService;

    private Context context;

    private GoogleApiClient googleApiClient;

    private LocationServiceListener locationServiceListener;

    private Geocoder geocoder;

    @Inject
    LocationServiceImpl(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        initGoogleApiClient();

        geocoder = new Geocoder(context, Locale.getDefault());
    }

    private void initGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }
    @Override
    public void setLocationServiceListener(LocationServiceListener locationServiceListener) {
        this.locationServiceListener = locationServiceListener;
    }

    @Override
    public void requestLocationUpdate() {
        if (isConnected()) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestLocationPermission();
            } else {
                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                notifyLocation(location);
            }
        }
    }

    private void notifyLocation(Location location) {
        if (location != null) {
            locationServiceListener.onLocation(location);
        } else {
            locationServiceListener.onLocationNotFound();
        }
    }

    private boolean isConnected() {
        return googleApiClient != null && (googleApiClient.isConnected() || googleApiClient.isConnecting());
    }

    @Override
    public void requestLocationPermission() {
        permissionService.requestPermissions(ConstantUtils.LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onStart() {
        connect();
    }

    private void connect() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        disconnect();
    }

    private void disconnect() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public String getCityNameByCoordinates(double lat, double lon) {
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null && addresses.size() > 0) {
                return addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            Timber.e(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public String getStreetByCoordinates(double lat, double lon) {
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null && addresses.size() > 0) {
                String street = addresses.get(0).getFeatureName();
                String number = addresses.get(0).getSubThoroughfare();
                return getCompleteStreetName(street, number);
            }
        } catch (IOException e) {
            Timber.e(e.getMessage());
            return null;
        }
        return null;
    }

    private String getCompleteStreetName(String street, String number) {
        if (number == null) {
            return street;
        }
        return context.getString(R.string.street_name, street, number);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public interface LocationServiceListener {
        void onLocation(Location location);

        void onLocationNotFound();
    }

}
