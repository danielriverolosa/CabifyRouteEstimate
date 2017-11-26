package com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute;


import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.domain.interactor.InteractorCallback;

import java.util.List;

public interface CalculateRouteInteractor {
    void execute(Route route, InteractorCallback<List<Estimate>> callback);
}
