package com.rivero.daniel.cabifyestimate.data.repository.estimate;

import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.ApiClientGenerator;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.EstimateApi;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateDataModel;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateRequest;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.VehicleDataModel;
import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.presentation.route.test.helper.CallFake;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstimateApiRepositoryTest {

    @InjectMocks
    private EstimateApiRepository repository;
    
    @Mock
    private ApiClientGenerator clientGenerator;
    
    @Mock
    private EstimateApi estimateApi;

    @Before
    public void setUp() throws Exception {
        when(clientGenerator.generateApi(EstimateApi.class)).thenReturn(estimateApi);
    }

    @Test
    public void getEstimateList_whenCallToApi_shouldMapToDomainEstimateCorrectly() throws Exception {
        when(estimateApi.calculateEstimate(any(EstimateRequest.class)))
                .thenReturn(CallFake.buildSuccess(buildEstimateList()));
        
        List<Estimate> estimateList = repository.getEstimateList(buildRoute());
        
        assertThat(estimateList, hasSize(1));
        assertThat(estimateList, hasItem(hasProperty("totalPrice", is(1000))));
        assertThat(estimateList, hasItem(hasProperty("priceFormatted", is("10,00 €"))));
    }

    private Route buildRoute() {
        return new Route.Builder()
                .destinyPlacement(buildPlacement())
                .originPlacement(buildPlacement())
                .build();
    }

    private Placement buildPlacement() {
        return new Placement.Builder()
                .latitude(2.22)
                .longitude(3.33)
                .build();
    }

    private List<EstimateDataModel> buildEstimateList() {
        List<EstimateDataModel> estimateDataModelList = new ArrayList<>();
        estimateDataModelList.add(buildEstimateDataModel());
        
        return estimateDataModelList;
    }
    
    private EstimateDataModel buildEstimateDataModel() {
       return new EstimateDataModel.Builder()
               .vehicle(buildVehicle())
               .totalPrice(1000)
               .priceFormatted("10,00 €")
               .build();
        
    }

    private VehicleDataModel buildVehicle() {
        return new VehicleDataModel.Builder()
                .name("Cabify Executive")
                .shortName("Executive")
                .description("Description")
                .icon(new VehicleDataModel.Icon())
                .build();
    }
}