package com.example.redi.MyFirstAndroidApp.models.http;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ReDI on 1/10/2017.
 */

public class RestClient {

    private static final String URL = "https://redi-free-wifi.herokuapp.com";
    private static final String USERNAME = "free_wifi@redi-school.org";
    private static final String PASSWORD = "y3LTqULBe45yRQ6R";
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String ACCEPT_KEY = "Accept";
    private static final String APPLICATION_JSON_VALUE = "application/json";


    private static final RestClient instance = new RestClient();

    private OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();

    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());


    private RestClient() {

    }

    public static RestClient getInstance() {
        return instance;
    }


    public <S> S createService(Class<S> serviceClass) {
        final String credentials = USERNAME + ":" + PASSWORD;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder reqBuilder = original.newBuilder()
                        .header(AUTHORIZATION_KEY, basic)
                        .header(ACCEPT_KEY, APPLICATION_JSON_VALUE)
                        .method(original.method(), original.body());

                Request request = reqBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(httpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }
}
