package com.lucasknezevich.AD340;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class TrafficCamListActivity extends AppCompatActivity {

    ArrayList<Camera> trafficCameras = new ArrayList<>();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_cam);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isWifiConnected = false;
        boolean isMobileConnected = false;

        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConnected |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConnected |= networkInfo.isConnected();
            }
        };

        if (isWifiConnected || isMobileConnected) {
            TrafficCamListAdapter camAdapter = new TrafficCamListAdapter(trafficCameras);
            Camera.getCameraData(this, trafficCameras, camAdapter);
            RecyclerView rv = findViewById(R.id.recyclerView_TrafficCams);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(camAdapter);

        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                    , getString(R.string.seattleTrafficCams_noNetworkMsg), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}