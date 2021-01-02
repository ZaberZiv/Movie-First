package com.movieziv.moviefirst.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TableMovies movies);

    @Query("DELETE FROM movie_table WHERE movie_id==:movieID")
    void deleteMovie(int movieID);

    @Query("SELECT * FROM movie_table")
    LiveData<List<TableMovies>> getAllMovies();

    @Query("SELECT * FROM movie_table WHERE movie_id==:movieID")
    LiveData<TableMovies> getMovieById(int movieID);
}
