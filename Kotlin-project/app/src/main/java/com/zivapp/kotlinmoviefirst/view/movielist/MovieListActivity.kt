package com.zivapp.kotlinmoviefirst.view.movielist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.adapters.MovieAdapter
import com.zivapp.kotlinmoviefirst.constants.Constants
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MovieListActivity"
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var mMovieAdapter: MovieAdapter
    private var page = 1
    private var flag = 0
    private var genreID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        getExtraData()
        pageButtons()
    }

    private fun getExtraData() {
        val arguments = intent.extras

        if (arguments != null) {
            if (arguments.getString(Constants.UPCOMING) == "upcoming") {
                viewModel.loadUpcomingMovies()
                flag = 1
            }
            if (arguments.getString(Constants.POPULAR) == "popular") {
                viewModel.loadPopularMovies()
                flag = 2
            }
            if (arguments.getString(Constants.TOP_RATED) == "top_rated") {
                viewModel.loadTopRatedMovies()
                flag = 3
            }
            if (arguments.getString(Constants.GENRES) != null) {
                genreID = arguments.getString(Constants.GENRES)!!.toInt()
                viewModel.loadGenresMovies(genreID)
                flag = 4
            }

            showMovies()
        }
    }

    private fun pageButtons() {
        button_previous.setOnClickListener(this)
        button_next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        pages(v)
        showMovies()
    }

    private fun pages(v: View?) {
        when (v) {
            button_previous -> {
                if (page > 1) {
                    page--
                    buttonState()
                    updatePage(page)
                } else {
                    button_previous.text = "-"
                    Toast.makeText(this, "No more pages!", Toast.LENGTH_SHORT).show()
                }
            }

            button_next -> {
                page++
                buttonState()
                updatePage(page)
            }
        }
    }

    private fun updatePage(page: Int) {
        if (flag == 1) viewModel.loadUpcomingMovies(page)
        if (flag == 2) viewModel.loadPopularMovies(page)
        if (flag == 3) viewModel.loadTopRatedMovies(page)
        if (flag == 4) viewModel.loadGenresMovies(genreID, page)
    }

    private fun showMovies() {
        viewModel.movieObjects.observe(this, {
            createMovieRecycler(recyclerAllMovies)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun buttonState() {
        button_previous.text = (page - 1).toString()
        current_page.text = page.toString()
        button_next.text = (page + 1).toString()
    }

    private fun createMovieRecycler(recycler: RecyclerView) {
        mMovieAdapter = MovieAdapter()
        recycler.layoutManager = setGridLayout()
        recycler.adapter = mMovieAdapter
    }

    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        )
    }
}