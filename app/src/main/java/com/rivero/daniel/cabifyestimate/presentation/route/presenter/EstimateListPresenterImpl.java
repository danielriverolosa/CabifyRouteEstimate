package com.rivero.daniel.cabifyestimate.presentation.route.presenter;

import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.interactor.InteractorCallback;
import com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute.CalculateRouteInteractor;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.BasePresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.view.EstimateListView;

import java.util.List;

import javax.inject.Inject;

@ViewScope
public class EstimateListPresenterImpl extends BasePresenter<EstimateListView> implements EstimateListPresenter, InteractorCallback<List<Estimate>> {

    private CalculateRouteInteractor interactor;

    @Inject
    EstimateListPresenterImpl(CalculateRouteInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onCreate(EstimateListView view) {
        super.onCreate(view);
        interactor.execute(getView().getRoute(), this);
    }

    @Override
    public void onSuccess(List<Estimate> data) {
        getView().showEstimateList(data);
    }

    @Override
    public void onError(Throwable t) {

    }
}
