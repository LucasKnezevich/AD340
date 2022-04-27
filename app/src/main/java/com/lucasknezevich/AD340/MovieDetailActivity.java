package com.lucasknezevich.AD340;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

//        Intent intent = getIntent();
//        String test = intent.getStringExtra("test");
//        Log.d("INTENT", test);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDefaultDisplayHomeAsUpEnabled(true);

        TextView movieTitle = findViewById(R.id.movie_detail_title);
        TextView movieYear = findViewById(R.id.movie_detail_year);
        ImageView movieImage = findViewById(R.id.movie_detail_image);
        TextView movieDescription = findViewById(R.id.movie_detail_description);


    }
}