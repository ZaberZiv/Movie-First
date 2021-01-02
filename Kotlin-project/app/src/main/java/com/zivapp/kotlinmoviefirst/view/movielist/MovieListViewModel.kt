package com.zivapp.kotlinmoviefirst.view.movielist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zivapp.kotlinmoviefirst.api.ApiFactory
import com.zivapp.kotlinmoviefirst.pojo.movies.MovieInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MovieListViewModel"
    private val compositeDisposable = CompositeDisposable()
    val movieObjects = MutableLiveData<List<MovieInfo>>()

    fun loadUpcomingMovies(page: Int = 1) {
        val disposable = ApiFactory.apiService.getUpcomingPosts(page = page)
                .map {
                    it.movieInfo }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movieObjects.postValue(it)
                    Log.v(TAG, "Success ALL Upcoming")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }

    fun loadPopularMovies(page: Int = 1) {
        val disposable = ApiFactory.apiService.getPopularPosts(page = page)
                .map {
                    it.movieInfo }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movieObjects.postValue(it)
                    Log.v(TAG, "Success ALL Popular")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }

    fun loadTopRatedMovies(page: Int = 1) {
        val disposable = ApiFactory.apiService.getTopRatedPosts(page = page)
                .map {
                    it.movieInfo }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movieObjects.postValue(it)
                    Log.v(TAG, "Success ALL TopRated")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }

    fun loadGenresMovies(genreID: Int, page: Int = 1) {
        val disposable = ApiFactory.apiService.getMoviesByGenres(genre_id = genreID, page = page)
                .map {
                    it.movieInfo }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movieObjects.postValue(it)
                    Log.v(TAG, "Success LoadGenresMovies")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }
}