package com.zivapp.kotlinmoviefirst.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zivapp.kotlinmoviefirst.pojo.movies.UpcomingMovies

@Dao
interface UpcomingMovieInfoDao {

    @Query("SELECT * FROM upcoming_movies")
    fun getUpcomingMovies() : LiveData<List<UpcomingMovies>>

    @Query("SELECT * FROM upcoming_movies WHERE id == :id")
    fun getDetailInfoAboutMovie(id: Int) : LiveData<UpcomingMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovieInfo(movieList: List<UpcomingMovies>)
}