package com.movieziv.moviefirst.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.database.TableMovies;
import com.movieziv.moviefirst.repository.DbRepository;
import com.movieziv.moviefirst.repository.RetrofitRepository;

import java.util.List;

// this Database ViewModel is for MovieDetailActivity and FavoritesFragment
public class DbViewModel extends ActivityViewModel {
    private DbRepository dbRepository;
    private RetrofitRepository retrofitRepository;

    public DbViewModel(@NonNull Application application) {
        super(application);
        dbRepository = new DbRepository(application);
        retrofitRepository = new RetrofitRepository();
    }

    public LiveData<List<TableMovies>> getMovies() {
        return dbRepository.getAllMovies();
    }

    public LiveData<TableMovies> getOneMovie(int id) {
        return dbRepository.getMovie(id);
    }

    public void getResultObject(List<TableMovies> list) {
        retrofitRepository.getListOfIds(list);
    }

    public void addFavMovie(TableMovies movie) {
        dbRepository.insert(movie);
    }

    public void deleteMovieVM(int id) {
        dbRepository.deleteMovieRepo(id);
    }
}
