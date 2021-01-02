package com.zivapp.kotlinmoviefirst.view.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.zivapp.kotlinmoviefirst.database.AppDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "FavoriteViewModel"
    private val db = AppDatabase.getInstance(application)

}