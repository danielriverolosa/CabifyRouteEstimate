package com.rivero.daniel.cabifyestimate.data.repository.estimate;

import com.rivero.daniel.cabifyestimate.data.repository.BaseRepository;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.ApiClientGenerator;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.EstimateApi;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateDataModel;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateRequest;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateRequest.StopLocation;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.VehicleDataModel;
import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.domain.Vehicle;
import com.rivero.daniel.cabifyestimate.domain.repository.EstimateRepository;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class EstimateApiRepository extends BaseRepository implements EstimateRepository {

    private ApiClientGenerator clientGenerator;

    @Inject
    public EstimateApiRepository(ApiClientGenerator clientGenerator) {
        this.clientGenerator = clientGenerator;
    }

    @Override
    public List<Estimate> getEstimateList(Route route) {
        EstimateApi estimateApi = clientGenerator.generateApi(EstimateApi.class);
        Call<List<EstimateDataModel>> call = estimateApi.calculateEstimate(buildRequest(route));

        List<EstimateDataModel> estimateDataModelList = executeCall(call);

        return CollectionUtils.collect(estimateDataModelList, this::buildEstimate, new ArrayList<>());
    }

    private EstimateRequest buildRequest(Route route) {
        StopLocation originStop = buildStopLocation(route.getOriginPlacement());
        StopLocation destinyStop = buildStopLocation(route.getDestinyPlacement());

        EstimateRequest estimateRequest = new EstimateRequest();
        estimateRequest.setOrigin(originStop);
        estimateRequest.setDestiny(destinyStop);

        return estimateRequest;
    }

    private StopLocation buildStopLocation(Placement placement) {
        StopLocation stopLocation = new StopLocation();
        stopLocation.setLatitude(placement.getLatitude());
        stopLocation.setLongitude(placement.getLongitude());

        return stopLocation;
    }

    private Estimate buildEstimate(EstimateDataModel estimateDataModel) {
        return new Estimate.Builder()
                .vehicle(buildVehicle(estimateDataModel.getVehicle()))
                .totalPrice(estimateDataModel.getTotalPrice())
                .priceFormatted(estimateDataModel.getPriceFormatted())
                .build();
    }

    private Vehicle buildVehicle(VehicleDataModel vehicleDataModel) {
        return new Vehicle.Builder()
                .name(vehicleDataModel.getName())
                .shortName(vehicleDataModel.getShortName())
                .description(vehicleDataModel.getDescription())
                .urlIcon(vehicleDataModel.getIcon().getIconUrl())
                .build();
    }
}
