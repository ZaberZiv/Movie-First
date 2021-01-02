package com.zivapp.kotlinmoviefirst.view.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.adapters.MovieAdapter
import com.zivapp.kotlinmoviefirst.pojo.movies.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private val TAG = "MovieDetailsActivity"
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var mMovieAdapter: MovieAdapter
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        getExtraData()
        borderStarButton()
        solidStarButton()
    }

    private fun getExtraData() {
        val argument = intent.extras

        if (argument != null) {
            val movieID = argument.getInt("movie_id")

            loadUpcomingMovieFromDB(movieID)
            loadPopularMovieFromDB(movieID)
            loadTopRatedMovieFromDB(movieID)
            loadSimilarMoviesFromWeb(movieID)
        } else {
            Log.v(TAG, "Argument is empty")
        }
    }

    private fun loadUpcomingMovieFromDB(movieID: Int) {
        viewModel.getUpcomingData(movieID).observe(this, {
            if (it != null) {
                Log.v(TAG, "UpcomingMovie: ${it.title}, ${it.id} ")
                setDataOnViews(it)
            } else {
                counter++
                Log.v(TAG, "Upcoming Nothing - counter: $counter")
                loadMovieDetailsFromWeb(movieID)
            }
        })
    }

    private fun loadPopularMovieFromDB(movieID: Int) {
        viewModel.getPopularData(movieID).observe(this, {
            if (it != null) {
                Log.v(TAG, "Popular: ${it.title}, ${it.id} ")
                setDataOnViews(it)
            } else {
                counter++
                Log.v(TAG, "Popular Nothing - counter: $counter")
                loadMovieDetailsFromWeb(movieID)
            }
        })
    }

    private fun loadTopRatedMovieFromDB(movieID: Int) {
        viewModel.getTopRatedData(movieID).observe(this, {
            if (it != null) {
                Log.v(TAG, "Top Rated: ${it.title}, ${it.id} ")
                setDataOnViews(it)
            } else {
                counter++
                Log.v(TAG, "TopRated Nothing - counter: $counter")
                loadMovieDetailsFromWeb(movieID)
            }
        })
    }

    private fun loadMovieDetailsFromWeb(movieID: Int) {
        if (counter >= 3) {
            viewModel.movieDetails(movieID)
            viewModel.movieDetailsObject.observe(this, {
                setDataOnViews(it)
            })
        } else {
            Log.v(TAG, "Data didn't loaded because There is data in database")
        }
    }

    private fun loadSimilarMoviesFromWeb(movieID: Int) {
        viewModel.loadSimilarMovies(movieID)
        viewModel.listOfSimilarMovies.observe(this, {
            createMovieRecycler(movie_recycler_similar_movies)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun setDataOnViews(movie: Movie) {
        movie_name.text = movie.title
        movie_genre.text = movie.genre
        movie_overview.text = movie.overview
        movie_rating.text = movie.voteAverage.toString()
        movie_release_date.text = movie.releaseDate
        Picasso.get().load(movie.getFullImageUrl()).into(movie_poster)

        if (movie.budget == null || movie.budget == 0) {
            movie_budget.text = "$0"
        } else {
            movie_budget.text = movie.budget.toString()
        }
    }

    private fun createMovieRecycler(recycler: RecyclerView) {
        mMovieAdapter = MovieAdapter()
        recycler.layoutManager = setGridLayout()
        recycler.adapter = mMovieAdapter
    }

    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(
                this,
                1,
                GridLayoutManager.HORIZONTAL,
                false
        )
    }

    private fun borderStarButton() {
        movie_star_favorites_border.setOnClickListener {
            Log.v(TAG, "borderStarButton() WORKS!")
            movie_star_favorites_border.visibility = View.GONE
            movie_star_favorites_solid.visibility = View.VISIBLE
        }
    }

    private fun solidStarButton() {
        movie_star_favorites_solid.setOnClickListener {
            Log.v(TAG, "solidStarButton() WORKS!")
            movie_star_favorites_solid.visibility = View.GONE
            movie_star_favorites_border.visibility = View.VISIBLE
        }
    }
}