package org.prod.retrofitexample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Neha on 5/19/17.
 */

public class Client {

    public static final String BASE_URL = "https://api.github.com/";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
