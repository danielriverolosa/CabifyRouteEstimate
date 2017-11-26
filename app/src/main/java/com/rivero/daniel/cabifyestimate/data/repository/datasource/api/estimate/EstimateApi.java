package com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate;


import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateDataModel;
import com.rivero.daniel.cabifyestimate.data.repository.datasource.api.estimate.model.EstimateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EstimateApi {

    @POST("/api/v2/estimate")
    Call<List<EstimateDataModel>> calculateEstimate(@Body EstimateRequest request);

}
