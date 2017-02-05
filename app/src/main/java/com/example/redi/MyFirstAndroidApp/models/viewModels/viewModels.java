package com.example.redi.MyFirstAndroidApp.models.viewModels;

import com.example.redi.MyFirstAndroidApp.models.entities.MyUser;
import com.example.redi.MyFirstAndroidApp.models.entities.Venue;

/**
 * Created by ReDI on 1/31/2017.
 */

public class viewModels {
    private Venue venue;
    private MyUser myUser;

    public viewModels(Venue venue, MyUser myUser) {
        this.venue = venue;
        this.myUser = myUser;
    }

    public Venue getVenue() {
        return venue;
    }

    public MyUser getMyUser() {
        return myUser;
    }
}
