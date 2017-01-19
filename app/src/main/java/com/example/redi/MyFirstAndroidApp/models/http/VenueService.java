package com.example.redi.MyFirstAndroidApp.models.http;


import com.example.redi.MyFirstAndroidApp.models.entities.Venue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ReDI on 1/10/2017.
 */

public interface VenueService {
    @GET("/venues")
    Call<List<Venue>> getVenues();

    @POST("/venues")
    Call<Void> createVenue(@Body Venue venue);

    @PATCH("/venues")
    Call<Void> updateVenue(@Body Venue venue);

    @DELETE("/venues/{id}")
    Call<Void> deleteVenue(@Path("id") long id);
}
