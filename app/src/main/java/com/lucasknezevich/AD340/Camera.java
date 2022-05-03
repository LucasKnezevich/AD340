package com.lucasknezevich.AD340;

public class Camera {

    private String description;
    private String image;
    private String type;

    public Camera(String description, String image, String type) {
        this.description = description;
        this.image = image;
        this.type = type;
    };

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
            return "http://www.seattle.gov/trafficcams/images/" + image;
        } else {
            return "http://images.wsdot.wa.gov/nw/" + image;
        }
    }

}
