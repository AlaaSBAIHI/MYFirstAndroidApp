package com.example.redi.MyFirstAndroidApp.models.http;

import com.example.redi.MyFirstAndroidApp.models.entities.MyUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ReDI on 1/5/2017.
 */

public interface MyUserService {
    @GET("users/{user}")
    Call<MyUser> getUser(@Path("user") String user);


}
