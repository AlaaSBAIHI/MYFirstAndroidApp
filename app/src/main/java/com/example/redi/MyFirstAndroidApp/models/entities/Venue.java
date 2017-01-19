package com.example.redi.MyFirstAndroidApp.models.entities;

import android.support.annotation.NonNull;

/**
 * Created by ReDI on 1/10/2017.
 */

public class Venue {
    private long id;
    private String name;
    private String description;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;

    public Venue(@NonNull String name, @NonNull String description, @NonNull String category, @NonNull String address, @NonNull Double latitude, @NonNull Double longitude) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.longitude = longitude;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public long getId() {
        return id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getName() {
        return name;
    }


    @NonNull
    public String getCategory() {
        return category;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public Double getLatitude() {
        return latitude;
    }

    @NonNull
    public Double getLongitude() {
        return longitude;
    }

}
