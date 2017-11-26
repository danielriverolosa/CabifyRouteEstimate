package com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute;


import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.domain.interactor.AsyncInteractor;
import com.rivero.daniel.cabifyestimate.domain.interactor.InteractorCallback;
import com.rivero.daniel.cabifyestimate.domain.repository.EstimateRepository;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.MainThreadExecutor;
import com.rivero.daniel.cabifyestimate.infrastructure.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

@ViewScope
public class CalculateRouteAsyncInteractor extends AsyncInteractor<List<Estimate>> implements CalculateRouteInteractor {

    private EstimateRepository repository;

    @Inject
    public CalculateRouteAsyncInteractor(ThreadExecutor threadExecutor, MainThreadExecutor mainThreadExecutor, EstimateRepository estimateRepository) {
        super(threadExecutor, mainThreadExecutor);
        this.repository = estimateRepository;
    }

    @Override
    public void execute(Route route, InteractorCallback<List<Estimate>> callback) {
        execute(callback, route);
    }

    @Override
    protected void runInBackground(Object... params) {
        Route route = (Route) params[0];
        List<Estimate> estimateList = repository.getEstimateList(route);

        notifySuccess(estimateList);
    }
}
