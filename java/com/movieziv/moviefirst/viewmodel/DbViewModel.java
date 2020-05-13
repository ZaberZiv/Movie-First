package com.movieziv.moviefirst.viewmodel;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.database.TableMovies;
import com.movieziv.moviefirst.eventbus.ResultEventBus;
import com.movieziv.moviefirst.fragments.FavoritesFragment;
import com.movieziv.moviefirst.repository.DbRepository;
import com.movieziv.moviefirst.repository.RetrofitRepository;
import com.movieziv.moviefirst.retrofit.movies.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// this Database ViewModel is for MovieDetailActivity and FavoritesFragment
public class DbViewModel extends ActivityViewModel {
    private DbRepository dbRepository;
    private RetrofitRepository retrofitRepository;
    private LiveData<List<TableMovies>> moviesList;
    private LiveData<TableMovies> movie;


    public DbViewModel(@NonNull Application application) {
        super(application);
        dbRepository = new DbRepository(application);
        retrofitRepository = new RetrofitRepository();
    }

    public LiveData<List<TableMovies>> getMovies() {
        moviesList = dbRepository.getAllMovies();
        return moviesList;
    }

    public LiveData<TableMovies> getOneMovie(int id) {
        movie = dbRepository.getMovie(id);
        return movie;
    }

    public void getResultobj(List<TableMovies> list) {
        retrofitRepository.getListOfIds(list);
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
