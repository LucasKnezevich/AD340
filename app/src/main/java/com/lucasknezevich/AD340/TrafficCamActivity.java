package com.lucasknezevich.AD340;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class TrafficCamActivity extends AppCompatActivity {

    Context context;
    ArrayList<Camera> trafficCameras = new ArrayList<>();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_cam);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        RecyclerView rv = findViewById(R.id.recyclerView_TrafficCams);
        rv.setLayoutManager(new LinearLayoutManager(this));
        TrafficCamAdapter camAdapter = new TrafficCamAdapter(trafficCameras);
        rv.setAdapter(camAdapter);

        context = getApplicationContext();
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
            RequestQueue queue = Volley.newRequestQueue(this);
            @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(Request.Method.GET
                    , url, null, response -> {
                        Log.d("CAMS", response.toString());

                        try{
                            JSONArray features = response.getJSONArray("Features");
                            for (int i = 1; i < features.length(); i++) {
                                JSONObject point = features.getJSONObject(i);

                                JSONArray cams = point.getJSONArray("Cameras");

                                for (int j = 0; j < cams.length(); j++) {
                                    JSONObject cam = cams.getJSONObject(j);
                                    Camera camera = new Camera(cam.getString("Description")
                                            , cam.getString("ImageUrl")
                                            , cam.getString("Type"));
    //                            Log.d("CAMERA", camera.getImageUrl());
                                    trafficCameras.add(camera);
                                }
                            }
                            camAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSON ERROR", "Error Message: " + error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                    , getString(R.string.seattleTrafficCams_noNetworkMsg), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}