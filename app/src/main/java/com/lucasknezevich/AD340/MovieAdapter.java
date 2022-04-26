package com.lucasknezevich.AD340;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    String[][] movies;

    public MovieAdapter(String[][] dataSet) {
        movies = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView movieTitle;
        private final TextView movieYear;
        private final ImageView movieImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_title);
            movieYear = itemView.findViewById(R.id.movie_year);
            movieImg = itemView.findViewById(R.id.movie_img);
        }

        public TextView getMovieTitle() {
            return movieTitle;
        }

        public TextView getMovieYear() {
            return movieYear;
        }

        public ImageView getMovieImg() {
            return movieImg;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMovieTitle().setText(movies[position][0]);
        holder.getMovieYear().setText(movies[position][1]);

        Picasso.get().load(movies[position][3]).into(holder.getMovieImg());
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }
}