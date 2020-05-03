package com.movieziv.moviefirst.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.repository.RetrofitRepository;

import java.util.List;
// this ViewModel is for Main Activity
public class ActivityViewModel extends AndroidViewModel {

    private RetrofitRepository retrofitRepository;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        retrofitRepository = new RetrofitRepository(application);
    }

    public LiveData<List<Result>> getUpcomingMovies() {
        return retrofitRepository.getUpcomingLiveData();
    }

    public LiveData<List<Result>> getPopularMovies() {
        return retrofitRepository.getPopularLiveData();
    }

    public LiveData<List<Result>> getTopRatedMovies() {
        return retrofitRepository.getTopRatedLiveData();
    }

    public LiveData<List<Result>> getGenresMovie() {
        return retrofitRepository.getGenreLiveData();
    }
}
