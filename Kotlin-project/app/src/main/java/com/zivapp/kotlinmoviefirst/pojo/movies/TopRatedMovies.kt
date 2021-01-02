package com.zivapp.kotlinmoviefirst.pojo.movies

import androidx.room.Entity

@Entity(tableName = "top_rated_movies")
open class TopRatedMovies : PopularMovies() {
}