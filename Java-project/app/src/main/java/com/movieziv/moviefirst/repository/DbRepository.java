package com.movieziv.moviefirst.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.model.database.MovieDatabase;
import com.movieziv.moviefirst.model.database.MoviesDao;
import com.movieziv.moviefirst.model.database.TableMovies;

import java.util.List;

public class DbRepository {

    private MoviesDao moviesDao;
    private LiveData<List<TableMovies>> allMovies;

    public DbRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        moviesDao = database.moviesDao();
        allMovies = moviesDao.getAllMovies();
    }

    public void insert(final TableMovies movies) {
        MovieDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDao.insert(movies);
            }
        });
    }

    public void deleteMovieRepo(final int id) {
        MovieDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDao.deleteMovie(id);
            }
        });
    }

    public LiveData<List<TableMovies>> getAllMovies() {
        return allMovies;
    }

    public LiveData<TableMovies> getMovie(int id) {
        return moviesDao.getMovieById(id);
    }
}
