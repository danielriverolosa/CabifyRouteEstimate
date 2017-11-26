package com.rivero.daniel.cabifyestimate.infrastructure.di.module;

import com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute.CalculateRouteAsyncInteractor;
import com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute.CalculateRouteInteractor;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @ViewScope
    CalculateRouteInteractor provideCalculateRouteInteractor(CalculateRouteAsyncInteractor interactor) {
        return interactor;
    }

}
