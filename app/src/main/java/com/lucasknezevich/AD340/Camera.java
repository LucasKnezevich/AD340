package com.lucasknezevich.AD340;

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

}
