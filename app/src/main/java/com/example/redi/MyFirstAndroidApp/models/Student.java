package com.example.redi.MyFirstAndroidApp.models;

import android.support.annotation.AnyThread;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by ReDI on 29/11/2016.
 */

@AnyThread
public class Student {

    private String name;

    private String gender;

    @DrawableRes
    private int image;


    public Student(@NonNull String name, @NonNull String gender, @DrawableRes int image) {
        this.name = name;
        this.gender = gender;
        this.image = image;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    @NonNull
    public int getImage() {
        return image;
    }
}
