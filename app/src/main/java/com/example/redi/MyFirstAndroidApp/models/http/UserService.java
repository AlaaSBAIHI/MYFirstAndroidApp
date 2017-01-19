package com.example.redi.MyFirstAndroidApp.models.http;

import com.example.redi.MyFirstAndroidApp.models.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ReDI on 1/5/2017.
 */

public interface UserService {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);


}
