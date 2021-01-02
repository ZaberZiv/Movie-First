package com.zivapp.kotlinmoviefirst.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.constants.Constants
import com.zivapp.kotlinmoviefirst.pojo.genre.Genres
import com.zivapp.kotlinmoviefirst.pojo.movies.Movie
import com.zivapp.kotlinmoviefirst.view.movielist.MovieListActivity
import kotlinx.android.synthetic.main.card_view_genres.view.*

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private val TAG = "GenreAdapter"

    var movieInfoList: List<Genres> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreText = itemView.cv_name_genre
        val cardView = itemView.cardView

        fun openMovieList(genreID: Int) {
            val intent = Intent(itemView.context, MovieListActivity::class.java)
            intent.putExtra(Constants.GENRES, genreID.toString())
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view_genres, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = movieInfoList[position]
        with(holder) {
            genreText.text = genre.genre

            cardView.setOnClickListener {

                genre.id?.let { it1 -> openMovieList(it1) }
            }
        }
    }

    override fun getItemCount(): Int {
        return movieInfoList.size
    }
}