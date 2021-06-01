package com.example.restservice.models.location;

import org.springframework.data.annotation.Id;

public class Location {
    @Id
    private String id;

    private double latitude;
    private double longitude;
    private String upazila;
    private String district;

    public Location(double latitude, double longitude, String upazila, String district) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.upazila = upazila;
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", upazila='" + upazila + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
