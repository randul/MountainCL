package com.example.mountaincl.ui.maps;

import android.graphics.Bitmap;

import java.sql.Blob;

public class MapLocations {
    private double lat;
    private double lng;
    private String locationName;
    private String locationDescription;
    private String image;


    public MapLocations(String LocationName, double lat, double lng, String locationDescription, String image){
        this.locationName = locationName;
        this.lat = lat;
        this.lng = lng;
        this.locationDescription = locationDescription;
        this.image = image;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLocationDescription() {
        return locationDescription;
    }
    public void setLocationDescription(String locationDescription) { this.locationDescription= locationDescription;}

    public String getimage() { return image; }
    public void String(String image) {
        this.image = image;
    }


}
