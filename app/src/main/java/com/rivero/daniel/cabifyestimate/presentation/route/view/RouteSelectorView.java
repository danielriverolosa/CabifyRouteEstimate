package com.rivero.daniel.cabifyestimate.presentation.route.view;


import com.rivero.daniel.cabifyestimate.domain.Placement;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseView;

public interface RouteSelectorView extends BaseView {

    void updateOrigin(Placement placement);

    void updateDestiny(Placement placement);

    void showDestiny();

    void checkDisableButton();

}
