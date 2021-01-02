package com.zivapp.kotlinmoviefirst.view.moviedetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zivapp.kotlinmoviefirst.api.ApiFactory
import com.zivapp.kotlinmoviefirst.database.AppDatabase
import com.zivapp.kotlinmoviefirst.pojo.movies.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "MovieDetailsViewModel"
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val listOfSimilarMovies = MutableLiveData<List<MovieInfo>>()
    val movieDetailsObject = MutableLiveData<MovieInfo>()

    fun getUpcomingData(id: Int) : LiveData<UpcomingMovies> {
        return db.upcomingMovieInfoDao().getDetailInfoAboutMovie(id)
    }

    fun getPopularData(id: Int) : LiveData<PopularMovies> {
        return db.popularMovieInfoDao().getDetailInfoAboutMovie(id)
    }

    fun getTopRatedData(id: Int) : LiveData<TopRatedMovies> {
        return db.topRatedMovieInfoDao().getDetailInfoAboutMovie(id)
    }

    fun loadSimilarMovies(id: Int) {
        val disposable = ApiFactory.apiService.getSimilarMovies(movie_id = id)
                .map {
                    it.movieInfo }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    listOfSimilarMovies.postValue(it)
                    Log.v(TAG, "Success Similar")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }

    fun movieDetails(id: Int) {
        val disposable = ApiFactory.apiService.getResultDetails(movie_id = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieDetailsObject.postValue(it)
                    Log.v(TAG, "Success Movie Details ${it.title}")
                }, {
                    Log.v(TAG, "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }
}