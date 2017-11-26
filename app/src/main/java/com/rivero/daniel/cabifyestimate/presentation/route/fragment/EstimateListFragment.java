package com.rivero.daniel.cabifyestimate.presentation.route.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Route;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.infrastructure.di.scope.ViewScope;
import com.rivero.daniel.cabifyestimate.presentation.base.BaseFragment;
import com.rivero.daniel.cabifyestimate.presentation.route.adapter.EstimateAdapter;
import com.rivero.daniel.cabifyestimate.presentation.route.presenter.EstimateListPresenter;
import com.rivero.daniel.cabifyestimate.presentation.route.view.EstimateListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@ViewScope
public class EstimateListFragment extends BaseFragment implements EstimateListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.recycler_estimate_list)
    RecyclerView recyclerEstimateList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    EstimateListPresenter presenter;

    EstimateAdapter adapter;

    public static EstimateListFragment getInstance(Route route) {
        EstimateListFragment fragment = new EstimateListFragment();
        fragment.setParamByClass(Route.class, route);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onCreate(this);
        initializeToolbar();
        adapter = new EstimateAdapter();

        recyclerEstimateList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerEstimateList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerEstimateList.setAdapter(adapter);
    }

    private void initializeToolbar() {
        toolbarTitle.setText(R.string.estimate_list_toolbar_title);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
    }

    @Override
    public void initializeInjector() {
        buildInjector(new ViewModule(this)).inject(this);
    }

    @Override
    public int getLayoutContainer() {
        return R.layout.fragment_estimate_list;
    }

    @Override
    public Route getRoute() {
        return getParamByClass(Route.class);
    }

    @Override
    public void showEstimateList(List<Estimate> data) {
        progressBar.setVisibility(View.GONE);
        recyclerEstimateList.setVisibility(View.VISIBLE);
        adapter.swapData(data);
    }
}
