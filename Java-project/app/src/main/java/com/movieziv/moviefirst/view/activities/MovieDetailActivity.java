package com.movieziv.moviefirst.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movieziv.moviefirst.model.database.TableMovies;
import com.movieziv.moviefirst.view.viewmodel.DbViewModel;
import com.movieziv.moviefirst.view.viewmodel.DetailsViewModel;
import com.movieziv.moviefirst.adapter.MoviesAdapter;
import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.model.retrofit.GlideImage;
import com.movieziv.moviefirst.model.retrofit.movies.Movies;
import com.movieziv.moviefirst.model.retrofit.movies.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_OBJECT = "object";
    public static final String TRAILER_URL = "https://www.youtube.com/watch?v=";

    private static int mMovie_ID;
    private static String mVideoPath;
    private int mActiveFlag;

    private DetailsViewModel mDetailsViewModel;
    private DbViewModel mDbViewModel;

    private RecyclerView mRecyclerView;
    private TextView mMovieNameTextView;
    private TextView mMovieOverviewTextView;
    private TextView mMovieAverageRatingTextView;
    private TextView mMovieReleaseDateTextView;
    private ImageView mPosterImageView;
    private ImageView mStarImageView;
    private TextView mMovieGenreTextView;
    private TextView mMovieBudgetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Arrow "back to main menu"
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // ViewModel
        mDetailsViewModel = new ViewModelProvider(this)
                .get(DetailsViewModel.class);

        mDbViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(DbViewModel.class);

        // set RecyclerView layout
        mRecyclerView = findViewById(R.id.movie_recycler_similar_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(
                MovieDetailActivity.this,
                1,
                RecyclerView.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);

        getViewsByID();
        getExtraFromIntent();
        mStarImageView = findViewById(R.id.movie_star_favorites);

        getGenreAndBudget();
        getSimilarMovies();
        getVideo();

        //CHECK ALL DB
        mDbViewModel.getMovies().observe(this, new Observer<List<TableMovies>>() {
            @Override
            public void onChanged(List<TableMovies> tableMovies) {
                for (TableMovies movies : tableMovies) {
                    Log.i("ALL MOVIES", "CHECK DATABASE: " + movies.getMovie_id() + ", " + movies.getFav_flag());
                }
            }
        });

        // GET ONE MOVIE BY ID
        mDbViewModel.getOneMovie(mMovie_ID).observe(this, new Observer<TableMovies>() {
            @Override
            public void onChanged(TableMovies movies) {
                try {
                    mActiveFlag = movies.getFav_flag();
                } catch (Exception e) {
                    e.printStackTrace();
                    mActiveFlag = 0;
                }
                Log.i("getOneMovie", "Load DB: id " + mMovie_ID + ", flag " + mActiveFlag);

                if (mActiveFlag == 1) {
                    mStarImageView.setImageResource(R.drawable.ic_star_black_24dp);
                }
            }
        });

        // STAR BUTTON
        mStarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActiveFlag == 1) {
                    mDbViewModel.deleteMovieVM(mMovie_ID);
                    mStarImageView.setImageResource(R.drawable.ic_star_border_black_24dp);
                } else {
                    mActiveFlag = 1;
                    mDbViewModel.addFavMovie(new TableMovies(mMovie_ID, mActiveFlag));
                    mStarImageView.setImageResource(R.drawable.ic_star_black_24dp);
                }
            }
        });
    }

    public void getViewsByID() {
        mStarImageView = findViewById(R.id.movie_star_favorites);
        mPosterImageView = findViewById(R.id.movie_poster);
        mMovieNameTextView = findViewById(R.id.movie_name);
        mMovieOverviewTextView = findViewById(R.id.movie_overview);
        mMovieAverageRatingTextView = findViewById(R.id.movie_rating);
        mMovieReleaseDateTextView = findViewById(R.id.movie_release_date);
        mMovieBudgetTextView = findViewById(R.id.movie_budget);
        mMovieGenreTextView = findViewById(R.id.movie_genre);
    }

    public void getExtraFromIntent() {
        // Get object from MoviesFragment.class by Intent;
        Bundle arguments = getIntent().getExtras();
        Result result = null;
        if (arguments != null) {
            result = (Result) arguments.getSerializable(EXTRA_MOVIE_OBJECT);
        }

        //Get image from URL (getPosterPath()) by Glide. Check GlideImage class;
        try {
            GlideImage.getImageFromURL(result.getPosterPath(), this, mPosterImageView);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // Set data from intent object
        mMovieNameTextView.setText(result.getTitle());
        mMovieOverviewTextView.setText(result.getOverview());
        mMovieAverageRatingTextView.setText(String.valueOf(result.getVoteAverage()));
        mMovieReleaseDateTextView.setText(result.getReleaseDate());

        // Get movie ID for query in JSONPlaceHolderApi interface and DataBase
        mMovie_ID = result.getMId();
    }

    // Get genre and budget from DetailsViewModel
    public void getGenreAndBudget() {
        mDetailsViewModel.getMovieDetails(mMovie_ID).observe(MovieDetailActivity.this,
                new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                // Get genre
                ArrayList<Result> movieGenreList = movies.getGenres();
                String genreName = "";
                for (int i = 0; i < movieGenreList.size(); i++) {
                    genreName += movieGenreList.get(i).getGenre() + " ";
                }

                // Get budget
                NumberFormat format = NumberFormat.getCurrencyInstance();
                format.setMaximumFractionDigits(0);
                format.setCurrency(Currency.getInstance("USD"));

                mMovieGenreTextView.setText(genreName);
                mMovieBudgetTextView.setText(String.valueOf(format.format(movies.getBudget())));
            }
        });
    }

    // Get similar movies from DetailsViewModel
    private void getSimilarMovies() {
        mDetailsViewModel.getSimilarMovie(mMovie_ID).observe(MovieDetailActivity.this,
                new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                adapterMethod(mRecyclerView, (ArrayList<Result>) resultsList, 0);
            }
        });
    }

    // Adapter
    private void adapterMethod(RecyclerView recyclerView, ArrayList<Result> list, int index) {
        MoviesAdapter adapter = new MoviesAdapter(list, MovieDetailActivity.this, index);
        recyclerView.setAdapter(adapter);
    }

    // Get url from DetailsViewModel
    private void getVideo() {
        // Create the observer which updates the UI.
        mDetailsViewModel.getVideoPath(mMovie_ID).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mVideoPath = s;
            }
        });
    }

    // WatchTrailer button send URL by Intent to WatchTrailerActivity;
    public void watchTrailer(View view) {
        if (!mVideoPath.equals("0")) {
            Intent intent = new Intent(this, WatchTrailerActivity.class);
            intent.putExtra(WatchTrailerActivity.EXTRA_URL, mVideoPath);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sorry there is no trailer for this movie.", Toast.LENGTH_LONG).show();
        }
    }
}