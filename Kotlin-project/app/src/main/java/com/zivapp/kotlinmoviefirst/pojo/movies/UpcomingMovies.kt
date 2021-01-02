package com.zivapp.kotlinmoviefirst.pojo.movies

import androidx.room.Entity

@Entity(tableName = "upcoming_movies")
open class UpcomingMovies() : Movie() {
}