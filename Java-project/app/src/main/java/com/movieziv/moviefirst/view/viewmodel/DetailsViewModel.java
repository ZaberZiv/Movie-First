package com.movieziv.moviefirst.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieziv.moviefirst.model.retrofit.movies.Movies;
import com.movieziv.moviefirst.model.retrofit.movies.Result;
import com.movieziv.moviefirst.repository.RetrofitRepository;

import java.util.List;

// this ViewModel is for MovieDetailsActivity, SearchFragment, FavoritesFragment
public class DetailsViewModel extends ViewModel {

    private RetrofitRepository retrofitRepository;

    public DetailsViewModel() {
        retrofitRepository = new RetrofitRepository();
    }

    public MutableLiveData<List<Result>> getSimilarMovie(int id) {
        return retrofitRepository.getSimilarMoviesLiveData(id);
    }

    public MutableLiveData<String> getVideoPath(int id) {
        return retrofitRepository.getVideoLiveData(id);
    }

    public MutableLiveData<List<Result>> getSearchMovie(String movieName) {
        return retrofitRepository.getSearchMoviesLiveData(movieName);
    }

    public MutableLiveData<List<Result>> getAllMovies(int currentPage, int genreId) {
        return retrofitRepository.getAllMoviesLiveData(currentPage, genreId);
    }

    public MutableLiveData<Movies> getMovieDetails(int id) {
        return retrofitRepository.getGenreAndBudgetLiveData(id);
    }
}
