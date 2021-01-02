package com.zivapp.kotlinmoviefirst.pojo.movies

import androidx.room.Entity

@Entity(tableName = "popular_movies")
open class PopularMovies : UpcomingMovies() {
}