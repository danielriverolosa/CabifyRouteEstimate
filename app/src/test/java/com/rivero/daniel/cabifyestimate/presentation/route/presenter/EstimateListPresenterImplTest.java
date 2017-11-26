package com.rivero.daniel.cabifyestimate.presentation.route.presenter;

import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.domain.interactor.calculateroute.CalculateRouteInteractor;
import com.rivero.daniel.cabifyestimate.infrastructure.Supplier;
import com.rivero.daniel.cabifyestimate.presentation.route.view.EstimateListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.rivero.daniel.cabifyestimate.presentation.route.test.helper.Answers.callBackAnswer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstimateListPresenterImplTest {

    @InjectMocks
    private EstimateListPresenterImpl presenter;

    @Mock
    private EstimateListView view;

    @Mock
    private CalculateRouteInteractor interactor;

    @Test
    public void onCreate_whenInitPresenterAndViewHasRoute_shouldLoadEstimateList() throws Exception {
        when(view.getRoute()).thenReturn(new Route.Builder().build());
        doAnswer(callBackAnswer((Supplier<ArrayList>) ArrayList::new)).when(interactor).execute(any(Route.class), any());

        presenter.onCreate(view);

        verify(view).showEstimateList(anyList());
    }

    @Test
    public void onCreate_whenInteractorFail_shouldShowMessage() throws Exception {
        when(view.getRoute()).thenReturn(new Route.Builder().build());
        doAnswer(callBackAnswer(Exception::new)).when(interactor).execute(any(Route.class), any());

        presenter.onCreate(view);

        verify(view).showMessage(anyString());
    }
}