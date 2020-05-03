package com.movieziv.moviefirst.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.retrofit.GlideImage;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.activities.MovieDetailActivity;
import com.movieziv.moviefirst.activities.ShowAllMoviesActivity;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<Result> mMoviesList;
    private Context mContext;
    private int mIndex;

    // index: 0 - is for poster type layout (card_view_for_item.xml);
    // index: 1 - is for genres type layout (card_view_genres.xml);
    // index: 2 - is for favorite type layout (card_view_favorites.xml);
    public MoviesAdapter(ArrayList<Result> moviesList, Context mContext, int index) {
        this.mMoviesList = moviesList;
        this.mContext = mContext;
        this.mIndex = index;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(@NonNull CardView v) {
            super(v);
            cardView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        Result clickedDetailsItem = mMoviesList.get(position);
                        Intent intent;
                        if (mIndex == 1) {
                            intent = new Intent(mContext, ShowAllMoviesActivity.class);
                            intent.putExtra("flag", 4);
                            int id_genre = mMoviesList.get(position).getMId();
                            intent.putExtra("id_genre", id_genre);
                            String menu_name = mMoviesList.get(position).getGenre();
                            intent.putExtra("menu", menu_name);

                        } else {
                            intent = new Intent(mContext, MovieDetailActivity.class);
                            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_OBJECT, clickedDetailsItem);

                        }
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv;

        if (mIndex == 1) {
            cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_genres, parent, false);
        } else if (mIndex == 2) {
            cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_favorites, parent, false);
        } else {
            cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_for_item, parent, false);
        }

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;

        TextView textView = cardView.findViewById(R.id.cv_name_movie);
        ImageView imageView = cardView.findViewById(R.id.cv_image_movie);
        TextView rating = cardView.findViewById(R.id.cv_rating_item);
        TextView genreTextView = cardView.findViewById(R.id.cv_name_genre);
        TextView releaseDate = cardView.findViewById(R.id.cv_release_date);

        if (mIndex == 1) {
            String genre = mMoviesList.get(position).getGenre();
            genreTextView.setText(String.valueOf(genre));

        } else {
            String movieTitle = mMoviesList.get(position).getTitle();
            double ratingNumber = mMoviesList.get(position).getVoteAverage();
            String releaseDateMovie = mMoviesList.get(position).getReleaseDate();

            if (ratingNumber >= 7) {
                rating.setBackgroundColor(Color.GREEN);
            } else if (ratingNumber >= 5) {
                rating.setBackgroundColor(Color.YELLOW);
            } else if (ratingNumber < 5) {
                rating.setBackgroundColor(Color.RED);
            }

            textView.setText(movieTitle);
            rating.setText(String.valueOf(ratingNumber));
            //Get image from URL (getPosterPath()) by Glide. Check GlideImage class;
            GlideImage.getImageFromURL(mMoviesList.get(position).getPosterPath(), mContext, imageView);

            if (mIndex == 2) {
                String genre = mMoviesList.get(position).getGenresList().get(0).getGenre();
                genreTextView.setText(String.valueOf(genre));
                releaseDate.setText(releaseDateMovie);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }
}
