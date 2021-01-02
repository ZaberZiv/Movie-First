package com.zivapp.kotlinmoviefirst.adapters

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.pojo.movies.Movie
import com.zivapp.kotlinmoviefirst.view.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.card_view_for_item.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val TAG = "MovieAdapter"
    private val MOVIE_ID = "movie_id"

    var movieInfoList: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster = itemView.cv_image_movie
        val tvRating = itemView.cv_rating_item
        val tvMovieTitle = itemView.cv_name_movie

        fun openMovieInfo(movie: Movie) {
            itemView.setOnClickListener {
                Log.v(TAG, "CLiCK! ${movie.title}, ${movie.id}")
                val intent = Intent(itemView.context, MovieDetailsActivity::class.java)
                intent.putExtra(MOVIE_ID, movie.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.card_view_for_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieInfoList[position]
        with(holder) {
            Picasso.get().load(movie.getFullImageUrl()).into(ivPoster)
            tvMovieTitle.text = movie.title

            ratingColors(tvRating, movie.voteAverage!!)
            tvRating.text = movie.voteAverage.toString()

            openMovieInfo(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieInfoList.size
    }

    private fun ratingColors(view: TextView, rate: Double) {
        when (rate.toInt()) {
            in 7..10 -> view.setBackgroundColor(Color.GREEN)
            in 5..6 -> view.setBackgroundColor(Color.YELLOW)
            else -> view.setBackgroundColor(Color.RED)
        }
    }
}