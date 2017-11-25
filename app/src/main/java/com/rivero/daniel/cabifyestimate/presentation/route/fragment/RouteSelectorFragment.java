package com.rivero.daniel.cabifyestimate.presentation.route.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseFragment;
import com.rivero.daniel.cabifyestimate.presentation.common.custom.PlaceSelector;
import com.rivero.daniel.cabifyestimate.presentation.common.utils.ConstantUtils;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.RouteSelectorPresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.utils.BitmapUtils;
import com.rivero.daniel.cabifyestimate.presentation.route.view.RouteSelectorView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.rivero.daniel.cabifyestimate.presentation.common.utils.ConstantUtils.PLACE_AUTOCOMPLETE_DESTINY_REQUEST_CODE;
import static com.rivero.daniel.cabifyestimate.presentation.common.utils.ConstantUtils.PLACE_AUTOCOMPLETE_ORIGIN_REQUEST_CODE;

public class RouteSelectorFragment extends BaseFragment implements RouteSelectorView, OnMapReadyCallback {

    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.place_selector_origin)
    PlaceSelector placeSelectorOrigin;
    @BindView(R.id.place_selector_destiny)
    PlaceSelector placeSelectorDestiny;
    @BindView(R.id.button_continue)
    Button continueButton;

    @Inject
    RouteSelectorPresenter presenter;

    private GoogleMap googleMap;

    private HashMap<Integer, Marker> hashMapMarker = new HashMap<>();

    public static RouteSelectorFragment getInstance() {
        return new RouteSelectorFragment();
    }

    @Override
    public void initializeInjector() {
        buildInjector(new ViewModule(this)).inject(this);
    }

    @Override
    public int getLayoutContainer() {
        return R.layout.fragment_route_selector;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onCreate(this);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        placeSelectorOrigin.setOnClickListener(v -> launchPlaceAutoCompleteSelector(PLACE_AUTOCOMPLETE_ORIGIN_REQUEST_CODE));
        placeSelectorDestiny.setOnClickListener(v -> launchPlaceAutoCompleteSelector(PLACE_AUTOCOMPLETE_DESTINY_REQUEST_CODE));
        continueButton.setOnClickListener(v -> onClickContinueButton());
    }

    private void launchPlaceAutoCompleteSelector(int requestCode) {
        try {
            Intent intent = getPlaceAutocomplete().build(getActivity());
            startActivityForResult(intent, requestCode);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            showMessage(e.getMessage());
        }
    }

    private PlaceAutocomplete.IntentBuilder getPlaceAutocomplete() {
        LatLngBounds spainBounds = new LatLngBounds(new LatLng(35.479099, -11.275417), new LatLng(43.875278, 5.201076));
        PlaceAutocomplete.IntentBuilder intentBuilder = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN);
        intentBuilder.setBoundsBias(spainBounds);
        return intentBuilder;
    }

    private void onClickContinueButton() {
        if (placeSelectorDestiny.isPlaceFilled()) {
            presenter.onClickContinue(placeSelectorOrigin.getPlacement(), placeSelectorDestiny.getPlacement());
        } else if (placeSelectorOrigin.isPlaceFilled()) {
            presenter.onClickShowPlaceSelectorDestiny();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_AUTOCOMPLETE_ORIGIN_REQUEST_CODE) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                presenter.updateOriginPlace(place);
            } else if (requestCode == PLACE_AUTOCOMPLETE_DESTINY_REQUEST_CODE) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                presenter.updateDestinyPlace(place);
            } else {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
                Timber.e(this.getClass().getSimpleName(), status.getStatusMessage());
            }
        }
    }

    @Override
    public void setMarker(LatLng latLng, int tag) {
        if (hashMapMarker.containsKey(tag)) {
            Marker oldMarker = hashMapMarker.get(tag);
            hashMapMarker.remove(tag);
            oldMarker.remove();
        }

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapUtils.bitmapDescriptorFromVector(getContext(), R.drawable.ic_marker));

        Marker marker = googleMap.addMarker(markerOptions);
        hashMapMarker.put(tag, marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getCameraPosition(), getResources().getDimensionPixelSize(R.dimen.map_view_padding)));
    }

    private LatLngBounds getCameraPosition() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Integer key : hashMapMarker.keySet()) {
            Marker marker = hashMapMarker.get(key);
            builder.include(marker.getPosition());
        }
        return builder.build();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case ConstantUtils.LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.requestLocation();
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStartLocationService();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        presenter.onResume();
        checkDisableButton();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStopLocationService();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMaxZoomPreference(19f);
        LatLng spainCoordinates = new LatLng(40.4168151, -3.7055567);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spainCoordinates, 6f));
    }

    @Override
    public void updateOrigin(Placement placement) {
        placeSelectorOrigin.updatePlacement(placement);
    }

    @Override
    public void updateDestiny(Placement placement) {
        placeSelectorDestiny.updatePlacement(placement);
    }

    @Override
    public void showDestiny() {
        continueButton.setText(getString(R.string.route_selector_button_continue_text));
        placeSelectorDestiny.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkDisableButton() {
        if (!placeSelectorOrigin.isPlaceFilled() || isDestinyFilled()) {
            continueButton.setAlpha(0.5f);
        } else {
            continueButton.setAlpha(1);
        }
    }

    private boolean isDestinyFilled() {
        return placeSelectorDestiny.getVisibility() == View.VISIBLE && !placeSelectorDestiny.isPlaceFilled();
    }
}
