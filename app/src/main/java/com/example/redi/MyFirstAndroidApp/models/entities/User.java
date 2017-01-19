package com.example.redi.MyFirstAndroidApp.models.entities;

import android.support.annotation.NonNull;

/**
 * Created by ReDI on 1/5/2017.
 */

public class User {
    private String id;

    private String name;

    private String email;

    public User(@NonNull String id, @NonNull String name, @NonNull String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }
}
