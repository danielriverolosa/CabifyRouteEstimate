package com.rivero.daniel.cabifyestimate.infrastructure.di.component;


import com.rivero.daniel.cabifyestimate.infrastructure.di.module.InteractorModule;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.PresenterModule;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.navigator.Navigator;
import com.rivero.daniel.cabifyestimate.presentation.route.RouteCalculatorActivity;
import com.rivero.daniel.cabifyestimate.presentation.route.fragment.RouteSelectorFragment;

import dagger.Component;

@ViewScope
@Component(dependencies = ApplicationComponent.class,
        modules = {ViewModule.class, PresenterModule.class, InteractorModule.class})
public interface ViewComponent {

    void inject(RouteCalculatorActivity routeCalculatorActivity);

    void inject(RouteSelectorFragment routeSelectorFragment);
}
