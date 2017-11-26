package com.rivero.daniel.cabifyestimate.presentation.route.view;


import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseView;

import java.util.List;

public interface EstimateListView extends BaseView {
    Route getRoute();

    void showEstimateList(List<Estimate> data);
}
