package com.movieziv.moviefirst.model.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static JSONPlaceHolderApi getService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(JSONPlaceHolderApi.class);
    }
}