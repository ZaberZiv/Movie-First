package com.zivapp.kotlinmoviefirst.view.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.zivapp.kotlinmoviefirst.api.ApiFactory
import com.zivapp.kotlinmoviefirst.database.AppDatabase
import com.zivapp.kotlinmoviefirst.pojo.movies.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MovieViewModel"
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val upcomingMoviesList = db.upcomingMovieInfoDao().getUpcomingMovies()
    val popularMoviesList = db.popularMovieInfoDao().getPopularMovies()
    val topRatedMoviesList = db.topRatedMovieInfoDao().getTopRatedMovies()
    val genresMoviesList = db.genresMovieInfoDao().getGenresMovies()

    init {
        loadData()
        loadGenresData()
    }

    private fun loadData() {
        val disposable = Single.zip(
            ApiFactory.apiService.getUpcomingPosts(page = 1),
            ApiFactory.apiService.getPopularPosts(page = 1),
            ApiFactory.apiService.getTopRatedPosts(page = 1),
            { type1, type2, type3 ->
                val list: MutableList<List<MovieInfo>> = ArrayList()
                list.add(type1.movieInfo as List<MovieInfo>)
                list.add(type2.movieInfo as List<MovieInfo>)
                list.add(type3.movieInfo as List<MovieInfo>)
                list
            })
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.upcomingMovieInfoDao().insertUpcomingMovieInfo(it[0] as List<UpcomingMovies>)
                db.popularMovieInfoDao().insertPopularMovieInfo(it[1] as List<PopularMovies>)
                db.topRatedMovieInfoDao().insertTopRatedMovieInfo(it[2] as List<TopRatedMovies>)

                Log.v(TAG, "Success")
            }, {
                Log.v(TAG, "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    private fun loadGenresData() {
        val disposable = ApiFactory.apiService.getListOfGenres()
            .map {
                it.genres }
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.genresMovieInfoDao().insertGenresMovieInfo(it)
                Log.v(TAG, "Success Genres")
            }, {
                Log.v(TAG, "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}