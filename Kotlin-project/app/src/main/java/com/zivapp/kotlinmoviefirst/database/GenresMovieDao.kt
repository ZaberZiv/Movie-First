package com.zivapp.kotlinmoviefirst.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zivapp.kotlinmoviefirst.pojo.genre.Genres

@Dao
interface GenresMovieDao {

    @Query("SELECT * FROM genres")
    fun getGenresMovies() : LiveData<List<Genres>>

    @Query("SELECT * FROM genres WHERE id == :id")
    fun getDetailInfoAboutMovie(id: Int) : LiveData<Genres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenresMovieInfo(movieList: List<Genres>?)
}