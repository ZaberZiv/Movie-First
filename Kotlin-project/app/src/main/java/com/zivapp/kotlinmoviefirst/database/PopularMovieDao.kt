package com.zivapp.kotlinmoviefirst.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zivapp.kotlinmoviefirst.pojo.movies.PopularMovies

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies() : LiveData<List<PopularMovies>>

    @Query("SELECT * FROM popular_movies WHERE id == :id")
    fun getDetailInfoAboutMovie(id: Int) : LiveData<PopularMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovieInfo(movieList: List<PopularMovies>)
}