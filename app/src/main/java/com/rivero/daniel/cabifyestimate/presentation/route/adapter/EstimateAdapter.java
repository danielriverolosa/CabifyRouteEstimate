package com.rivero.daniel.cabifyestimate.presentation.route.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.Estimate;
import com.rivero.daniel.cabifyestimate.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class EstimateAdapter extends RecyclerView.Adapter<EstimateAdapter.ViewHolder> {

    private List<Estimate> estimateList;

    public EstimateAdapter() {
        estimateList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estimate, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(estimateList.get(position));
    }

    @Override
    public int getItemCount() {
        return estimateList.size();
    }

    public void swapData(List<Estimate> estimateList) {
        this.estimateList.addAll(estimateList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView price;
        private ImageView imageDescription;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_name);
            description = itemView.findViewById(R.id.text_description);
            price = itemView.findViewById(R.id.text_price);
            imageDescription = itemView.findViewById(R.id.image_description);
        }

        void bind(Estimate estimate) {
            Vehicle vehicle = estimate.getVehicle();

            name.setText(vehicle.getShortName());
            description.setText(vehicle.getDescription());
            price.setText(estimate.getPriceFormatted());

            Glide.with(itemView.getContext())
                    .load(vehicle.getUrlIcon())
                    .into(imageDescription);
        }
    }

}
