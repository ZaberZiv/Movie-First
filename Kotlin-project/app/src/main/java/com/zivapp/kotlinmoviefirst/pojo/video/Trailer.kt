package com.zivapp.kotlinmoviefirst.pojo.video

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Trailer {

    @SerializedName("results")
    @Expose
    val results: List<MediaVideo>? = null
}