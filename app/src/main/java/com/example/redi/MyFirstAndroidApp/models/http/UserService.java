package com.example.redi.MyFirstAndroidApp.models.http;

import com.example.redi.MyFirstAndroidApp.models.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ReDI on 2/5/2017.
 */

public interface UserService {
    @GET("/user/{id}")
    Call<User> getUserById(@Path("id") long id);

    @GET("/user")
    Call<List<User>> getUsers();

    @POST("/user")
    Call<User> createUser(@Body User user);

    @PATCH("/user/{id}")
    Call<User> updateUser(@Path("id") long id, @Body User user);

    @DELETE("/user/{id}")
    Call<Void> deleteUser(@Path("id") long id);
}
