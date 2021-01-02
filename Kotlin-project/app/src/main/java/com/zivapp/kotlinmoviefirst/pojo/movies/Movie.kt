package com.zivapp.kotlinmoviefirst.pojo.movies

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zivapp.kotlinmoviefirst.api.ApiFactory

abstract class Movie {

    @SerializedName("poster_path")
    @Expose
    open var posterPath: String? = null

    @SerializedName("title")
    @Expose
    open var title: String? = null

    @SerializedName("vote_average")
    @Expose
    open var voteAverage: Double? = null

    @SerializedName("overview")
    @Expose
    open var overview: String? = null

    @SerializedName("release_date")
    @Expose
    open var releaseDate: String? = null

    @SerializedName("budget")
    @Expose
    open var budget: Int? = null

    @SerializedName("name")
    @Expose
    open var genre: String? = null

    @PrimaryKey
    @SerializedName("id")
    @Expose
    open var id: Int? = null

    fun getFullImageUrl(): String {
        return ApiFactory.BASE_IMAGE_URL + posterPath
    }
}