package com.movieziv.moviefirst.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.database.TableMovies;
import com.movieziv.moviefirst.repository.DbRepository;

import java.util.List;

// this Database ViewModel is for MovieDetailActivity and FavoritesFragment
public class DbViewModel extends ActivityViewModel {
    private DbRepository dbRepository;
    private LiveData<List<TableMovies>> moviesList;
    private LiveData<TableMovies> movie;

    public DbViewModel(@NonNull Application application) {
        super(application);
        dbRepository = new DbRepository(application);
    }

    public LiveData<List<TableMovies>> getMovies() {
        moviesList = dbRepository.getAllMovies();
        return moviesList;
    }

    public LiveData<TableMovies> getOneMovie(int id) {
        movie = dbRepository.getMovie(id);
        return movie;
    }

    public void addFavMovie(TableMovies movie) {
        dbRepository.insert(movie);
    }

    public void updateFavMovie(TableMovies movie) {
        dbRepository.update(movie);
    }

    public void deleteFavMovie(TableMovies movie) {
        dbRepository.delete(movie);
    }

    public void deleteAllMovie() {
        dbRepository.deleteAllMovies();
    }
}
