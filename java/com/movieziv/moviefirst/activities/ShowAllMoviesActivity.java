package com.movieziv.moviefirst.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.adapter.MoviesAdapter;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.viewmodel.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllMoviesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mMenuNameTextView;
    private DetailsViewModel mModel;

    public static int mCurrentPageJSONApi;
    public static int mFlag;
    public static int mGenreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_movies);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mMenuNameTextView = findViewById(R.id.show_all_menu_name);
        mMenuNameTextView.setText("All Movies");

        mModel = new ViewModelProvider(this)
                .get(DetailsViewModel.class);

        mRecyclerView = findViewById(R.id.recyclerAllMovies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mCurrentPageJSONApi = 1;
        refreshPage();
    }

    public void changePage(View view) {
        Button btnPrev = findViewById(R.id.button_previous);
        Button btnNext = findViewById(R.id.button_next);
        TextView currentPage = findViewById(R.id.current_page);

        switch (view.getId()) {
            case R.id.button_previous:
                if (mCurrentPageJSONApi > 1) {
                    mCurrentPageJSONApi--;
                    btnPrev.setText(String.valueOf(mCurrentPageJSONApi - 1));
                    currentPage.setText(String.valueOf(mCurrentPageJSONApi));
                    btnNext.setText(String.valueOf(mCurrentPageJSONApi + 1));
                    refreshPage();
                } else {
                    btnPrev.setText("-");
                    Toast.makeText(this, "No more pages!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_next:
                mCurrentPageJSONApi++;
                btnPrev.setText(String.valueOf(mCurrentPageJSONApi - 1));
                currentPage.setText(String.valueOf(mCurrentPageJSONApi));
                btnNext.setText(String.valueOf(mCurrentPageJSONApi + 1));
                refreshPage();
                break;
        }
    }

    private void refreshPage() {
        Bundle arguments = getIntent().getExtras();
        mFlag = arguments.getInt("flag");
        mGenreId = arguments.getInt("id_genre");
        String menu_name = arguments.getString("menu");

        switch (mFlag) {
            case 1:
                mMenuNameTextView.setText(menu_name);
                break;
            case 2:
                mMenuNameTextView.setText(menu_name);
                break;
            case 3:
                mMenuNameTextView.setText(menu_name);
                break;
            case 4:
                // Intent (flag, menu_name, id_genre) comes from MoviesAdapter.class;
                mMenuNameTextView.setText(menu_name);
                break;
        }

        mModel.getAllMovies(mCurrentPageJSONApi, mGenreId).observe(ShowAllMoviesActivity.this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultslist) {
                adapterMethod(mRecyclerView, (ArrayList<Result>) resultslist, 0);
            }
        });
    }

    private void adapterMethod(RecyclerView recyclerView, ArrayList<Result> list, int index) {
        MoviesAdapter adapter = new MoviesAdapter(list, ShowAllMoviesActivity.this, index);
        recyclerView.setAdapter(adapter);
    }
}
