package com.movieziv.moviefirst.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert
    void insert(TableMovies movies);

    @Update
    void update(TableMovies movies);

    @Delete
    void delete(TableMovies movies);

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table")
    LiveData<List<TableMovies>> getAllMovies();

    @Query("SELECT * FROM movie_table WHERE movie_id==:movieID")
    LiveData<TableMovies> getMovieById(int movieID);
}
