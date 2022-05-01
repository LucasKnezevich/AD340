package com.lucasknezevich.AD340;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class TrafficCamAdapter extends RecyclerView.Adapter<TrafficCamAdapter.ViewHolder> {

    String[] testData;

    public TrafficCamAdapter(String[] data) {
        testData = data;
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView camLocation;
        private final ImageView camImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            camLocation = itemView.findViewById(R.id.trafficCam_location);
            camImage = itemView.findViewById(R.id.trafficCam_img);
        }

        public TextView getCamLocation() {
            return camLocation;
        };

        public ImageView getCamImage() {
            return camImage;
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_cams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficCamAdapter.ViewHolder holder, int position) {
        holder.getCamLocation().setText(testData[0]);

        Picasso.get().load(testData[1]).into(holder.getCamImage());
    }

    @Override
    public int getItemCount() {
        return testData.length;
    }



}
