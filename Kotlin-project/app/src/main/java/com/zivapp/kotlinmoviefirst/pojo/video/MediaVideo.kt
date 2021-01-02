package com.zivapp.kotlinmoviefirst.pojo.video

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MediaVideo {
    @SerializedName("key")
    @Expose
    var key: String? = null
}