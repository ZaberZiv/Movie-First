package com.zivapp.kotlinmoviefirst.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zivapp.kotlinmoviefirst.pojo.movies.TopRatedMovies

@Dao
interface TopRatedMovieDao {

    @Query("SELECT * FROM top_rated_movies")
    fun getTopRatedMovies() : LiveData<List<TopRatedMovies>>

    @Query("SELECT * FROM top_rated_movies WHERE id == :id")
    fun getDetailInfoAboutMovie(id: Int) : LiveData<TopRatedMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovieInfo(movieList: List<TopRatedMovies>)
}