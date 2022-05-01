package com.lucasknezevich.AD340;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class TrafficCamActivity extends AppCompatActivity {

    Context context;
    ArrayList<Camera> cameras;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_cam);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        cameras = new ArrayList<>();



        RecyclerView rv = findViewById(R.id.recyclerView_TrafficCams);
        rv.setLayoutManager(new LinearLayoutManager(this));
        TrafficCamAdapter camAdapter = new TrafficCamAdapter(
                new String[]{getString(R.string.seattleTrafficCams_imageDescription)
                        , "https://picsum.photos/200/300"});
        rv.setAdapter(camAdapter);
    }
}