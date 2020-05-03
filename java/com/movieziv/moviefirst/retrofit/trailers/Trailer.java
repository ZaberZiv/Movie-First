package com.movieziv.moviefirst.retrofit.trailers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Trailer {

    @SerializedName("results")
    @Expose
    private ArrayList<MediaVideo> results = null;

    public ArrayList<MediaVideo> getResults() {
        return results;
    }

    public void setResults(ArrayList<MediaVideo> results) {
        this.results = results;
    }

}
