package com.rivero.daniel.cabifyroutestimate.infrastructure.di.component;


import com.rivero.daniel.cabifyroutestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyroutestimate.infrastructure.di.scope.ViewScope;

import dagger.Component;

@ViewScope
@Component(dependencies = ApplicationComponent.class,
        modules = ViewModule.class)
public interface ViewComponent {

}
