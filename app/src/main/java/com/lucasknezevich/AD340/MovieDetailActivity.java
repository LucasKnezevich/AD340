package com.lucasknezevich.AD340;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        Bundle bundle = this.getIntent().getExtras();
        String[] movieDetails = bundle.getStringArray("MovieDetail");

        TextView movieTitle = findViewById(R.id.movie_detail_title);
        TextView movieYear = findViewById(R.id.movie_detail_year);
        ImageView movieImage = findViewById(R.id.movie_detail_image);
        TextView movieDirector = findViewById(R.id.movie_detail_director);
        TextView movieDescription = findViewById(R.id.movie_detail_description);

        movieTitle.setText(movieDetails[0]);
        movieYear.setText(movieDetails[1]);

        movieDirector.setText(String.format("%s %s",getString(R.string.movieDetail_director),
                movieDetails[2]));

        Picasso.get().load(movieDetails[3]).into(movieImage);
        movieDescription.setText(movieDetails[4]);
    }
}