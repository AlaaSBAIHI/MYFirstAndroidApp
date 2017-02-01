package com.example.redi.MyFirstAndroidApp.models.viewModels;

import com.example.redi.MyFirstAndroidApp.models.entities.User;
import com.example.redi.MyFirstAndroidApp.models.entities.Venue;

/**
 * Created by ReDI on 1/31/2017.
 */

public class viewModels {
    private Venue venue;
    private User user;

    public viewModels(Venue venue, User user) {
        this.venue = venue;
        this.user = user;
    }

    public Venue getVenue() {
        return venue;
    }

    public User getUser() {
        return user;
    }
}
