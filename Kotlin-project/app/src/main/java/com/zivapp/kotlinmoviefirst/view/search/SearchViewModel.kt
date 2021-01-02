package com.zivapp.kotlinmoviefirst.view.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zivapp.kotlinmoviefirst.api.ApiFactory
import com.zivapp.kotlinmoviefirst.database.AppDatabase
import com.zivapp.kotlinmoviefirst.pojo.movies.MovieInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SearchViewModel"
    private val compositeDisposable = CompositeDisposable()
    var mutableData = MutableLiveData<List<MovieInfo>>()

    fun findMovie(title: String) {
        val disposable = ApiFactory.apiService.getSearchMovies(query = title)
            .map {
                it.movieInfo
            }
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                mutableData.postValue(it)
                Log.v(TAG, "Success: $it")
            }, {
                Log.v(TAG, "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }
}