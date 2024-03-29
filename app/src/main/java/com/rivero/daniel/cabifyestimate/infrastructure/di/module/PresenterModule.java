package com.rivero.daniel.cabifyestimate.infrastructure.di.module;

import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.EstimateListPresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.EstimateListPresenterImpl;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.RouteSelectorPresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.RouteSelectorPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    
    @Provides
    @ViewScope
    RouteSelectorPresenter provideRouteSelectorPresenter(RouteSelectorPresenterImpl routeSelectorPresenter) {
        return routeSelectorPresenter;
    }

    @Provides
    @ViewScope
    EstimateListPresenter provideEstimateListPresenter(EstimateListPresenterImpl estimateListPresenter) {
        return estimateListPresenter;
    }
}
