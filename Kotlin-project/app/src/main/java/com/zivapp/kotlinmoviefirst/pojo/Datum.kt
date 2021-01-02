package com.zivapp.kotlinmoviefirst.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.zivapp.kotlinmoviefirst.pojo.genre.Genres
import com.zivapp.kotlinmoviefirst.pojo.movies.MovieInfo

class Datum {
    @SerializedName("results")
    @Expose
    val movieInfo: List<MovieInfo>? = null

    @SerializedName("genres")
    @Expose
    val genres: List<Genres>? = null

    @SerializedName("budget")
    @Expose
    val budget = 0
}