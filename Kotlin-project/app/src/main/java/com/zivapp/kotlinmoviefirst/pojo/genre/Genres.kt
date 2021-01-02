package com.zivapp.kotlinmoviefirst.pojo.genre

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genres")
data class Genres(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int? = null,

        @SerializedName("name")
        @Expose
        var genre: String? = null
)