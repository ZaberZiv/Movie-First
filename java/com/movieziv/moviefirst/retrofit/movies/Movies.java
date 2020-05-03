package com.movieziv.moviefirst.retrofit.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movies {
    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;

    public ArrayList<Result> getResults() {
        return results;
    }

    @SerializedName("genres")
    @Expose
    private ArrayList<Result> genres = null;

    public ArrayList<Result> getGenres() {
        return genres;
    }

    @SerializedName("budget")
    @Expose
    private int budget;

    public int getBudget() {
        return budget;
    }

//    @SerializedName("title")
//    @Expose
//    private String title;
//
//    public String getTitle() {
//        return title;
//    }
//
//    @SerializedName("poster_path")
//    @Expose
//    private String poster_path;
//
//    public String getPosterPath() {
//        return poster_path;
//    }

//    @SerializedName("production_countries")
//    @Expose
//    private ArrayList<Result> productionCountries = null;
//
//    public ArrayList<Result> getProductionCountries() {
//        return productionCountries;
//    }
}
