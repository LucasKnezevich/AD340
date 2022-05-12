package com.lucasknezevich.AD340;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

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

public class Camera {

    private String id;
    private Double latitude;
    private Double longitude;
    private String description;
    private String image;
    private String type;

    public Camera(String id, Double latitude, Double longitude, String description, String image, String type) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.image = image;
        this.type = type;
    };

    public String getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        if (type.equals("sdot")) {
            return "https://www.seattle.gov/trafficcams/images/" + image;
        } else {
            return "https://images.wsdot.wa.gov/nw/" + image;
        }
    }


    public static void getCameraData(Context context, ArrayList<Camera> cameraArrayList,
                                     TrafficCamListAdapter adapter) {
        String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";


        RequestQueue queue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest =
            new JsonObjectRequest(Request.Method.GET
                    , url, null, response -> {
                // Log.d("CAMS", response.toString());

                try{
                    JSONArray features = response.getJSONArray("Features");
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject point = features.getJSONObject(i);
                        JSONArray coordinates = point.getJSONArray("PointCoordinate");

                        JSONArray cams = point.getJSONArray("Cameras");

                        for (int j = 0; j < cams.length(); j++) {
                            JSONObject cam = cams.getJSONObject(j);
                            Camera camera = new Camera(cam.getString("Id")
                                    , coordinates.getDouble(0)
                                    , coordinates.getDouble(1)
                                    , cam.getString("Description")
                                    , cam.getString("ImageUrl")
                                    , cam.getString("Type")
                            );
                            // Log.d("CAMERA", camera.getImageUrl());
                            cameraArrayList.add(camera);
                        }
                    }

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.d("ERROR MESSAGE", e.getMessage());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSON ERROR", "Error Message: " + error.getMessage());
                }
            });
        queue.add(jsonObjectRequest);
    }
}
