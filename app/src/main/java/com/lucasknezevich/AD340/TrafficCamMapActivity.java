package com.lucasknezevich.AD340;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Objects;

public class TrafficCamMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private boolean locationPermissionGranted;

    private static final String TAG = TrafficCamMapActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private boolean AddressRequested;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_cam_map);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        Log.d("MAP", "MAP READY");

        LatLng spaceNeedle = new LatLng(47.6205,-122.3496);
        map.addMarker(new MarkerOptions()
                .position(spaceNeedle)
                .title("Space Needle"))
                .showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(spaceNeedle,12));

        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();

//        ArrayList<Camera> cameras = new ArrayList<>();
//        Camera.getCameraData(this, cameras, null);
//        showCameraMarkers(cameras);
    }

    private void showCameraMarkers(ArrayList<Camera> camList) {
        for (Camera cam : camList) {
            Log.d("CAM", cam.getDescription());
            map.addMarker( new MarkerOptions().position(new LatLng(cam.getLatitude(),
                    cam.getLongitude())).title(cam.getDescription()));
        }
        Log.d("TEST", "TEST");
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastLocation = null;
                // getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            lastLocation = task.getResult();
                            if (lastLocation != null) {
                                LatLng lastCoordinates = new LatLng(lastLocation.getLatitude(),
                                        lastLocation.getLongitude());
                                map.addMarker(new MarkerOptions()
                                        .position(lastCoordinates)
                                        .title("Your Location: " + lastCoordinates.toString())
                                        .icon(BitmapDescriptorFactory
                                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
                                        .showInfoWindow();
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastCoordinates
                                        , 12));
                            } else {
                                Log.d(TAG, "Current location null, using defaults");
                            }
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: ", e.getMessage());
        }
    }

}