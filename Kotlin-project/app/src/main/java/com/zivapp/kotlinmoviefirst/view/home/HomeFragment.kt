package com.zivapp.kotlinmoviefirst.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zivapp.kotlinmoviefirst.adapters.GenreAdapter
import com.zivapp.kotlinmoviefirst.adapters.MovieAdapter
import com.zivapp.kotlinmoviefirst.constants.Constants
import com.zivapp.kotlinmoviefirst.databinding.FragmentHomeBinding
import com.zivapp.kotlinmoviefirst.view.movielist.MovieListActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var mMovieAdapter: MovieAdapter
    private lateinit var mGenreAdapter: GenreAdapter

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        loadUpcomingMoviesInfo()
        loadPopularMoviesInfo()
        loadTopRatedMoviesInfo()
        loadGenresMovieInfo()

        seeAllMoviesButtons(binding.fragMoviesSeeAllBtn1)
        seeAllMoviesButtons(binding.fragMoviesSeeAllBtn2)
        seeAllMoviesButtons(binding.fragMoviesSeeAllBtn3)

        return view
    }

    private fun loadUpcomingMoviesInfo() {
        viewModel.upcomingMoviesList.observe(viewLifecycleOwner, {
            Log.v(TAG, "Upcoming movies")
            createMovieRecycler(frag_movies_recycler_upcoming)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun loadPopularMoviesInfo() {
        viewModel.popularMoviesList.observe(viewLifecycleOwner, {
            Log.v(TAG, "Popular movies")
            createMovieRecycler(frag_movies_recycler_popular)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun loadTopRatedMoviesInfo() {
        viewModel.topRatedMoviesList.observe(viewLifecycleOwner, {
            Log.v(TAG, "Top Rated movies")
            createMovieRecycler(frag_movies_recycler_top_rated)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun loadGenresMovieInfo() {
        viewModel.genresMoviesList.observe(viewLifecycleOwner, {
            Log.v(TAG, "Genres")
            createGenreRecycler(frag_movies_recycler_genres)
            mGenreAdapter.movieInfoList = it
        })
    }

    private fun createMovieRecycler(recycler: RecyclerView) {
        mMovieAdapter = MovieAdapter()
        recycler.layoutManager = setGridLayout()
        recycler.adapter = mMovieAdapter
    }

    private fun createGenreRecycler(recycler: RecyclerView) {
        mGenreAdapter = GenreAdapter()
        recycler.layoutManager = setGridLayout()
        recycler.adapter = mGenreAdapter
    }

    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(
                activity,
                1,
                GridLayoutManager.HORIZONTAL,
                false
        )
    }

    private fun seeAllMoviesButtons(v: View?) {
        Log.v(TAG, "seeAllMoviesButtons!")

        v?.setOnClickListener {
            Log.v(TAG, "Clicked!")

            val intent = Intent(activity, MovieListActivity::class.java)
            when (v) {
                frag_movies_see_all_btn1 -> intent.putExtra(Constants.UPCOMING, "upcoming")
                frag_movies_see_all_btn2 -> intent.putExtra(Constants.POPULAR, "popular")
                frag_movies_see_all_btn3 -> intent.putExtra(Constants.TOP_RATED, "top_rated")
            }
            activity?.startActivity(intent)
        }
    }
}