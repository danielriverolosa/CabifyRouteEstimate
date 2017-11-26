package com.rivero.daniel.cabifyestimate.domain.repository;


import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Route;

import java.util.List;

public interface EstimateRepository {
    List<Estimate> getEstimateList(Route route);
}
