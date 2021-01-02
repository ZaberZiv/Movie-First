package com.movieziv.moviefirst.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieziv.moviefirst.adapter.MoviesAdapter;
import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.model.retrofit.movies.Result;
import com.movieziv.moviefirst.view.viewmodel.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    private RecyclerView mRecyclerUpcoming;
    private RecyclerView mRecyclerPopular;
    private RecyclerView mRecyclerTopRated;
    private RecyclerView mRecyclerGenres;
    private ActivityViewModel mActivityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        mRecyclerUpcoming = setGridLayoutManager(view, R.id.frag_movies_recycler_upcoming);
        mRecyclerPopular = setGridLayoutManager(view, R.id.frag_movies_recycler_popular);
        mRecyclerTopRated = setGridLayoutManager(view, R.id.frag_movies_recycler_top_rated);
        mRecyclerGenres = setGridLayoutManager(view, R.id.frag_movies_recycler_genres);

        mActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(ActivityViewModel.class);

        getDataFromViewModel();

        return view;
    }

    private RecyclerView setGridLayoutManager(View view, int recyclerid) {
        RecyclerView recycler = view.findViewById(recyclerid);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        return recycler;
    }

    private void getDataFromViewModel() {
        mActivityViewModel.getUpcomingMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                adapterMethod(mRecyclerUpcoming, (ArrayList<Result>) resultsList,0);
            }
        });

        mActivityViewModel.getPopularMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                adapterMethod(mRecyclerPopular, (ArrayList<Result>) resultsList,0);
            }
        });

        mActivityViewModel.getTopRatedMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                adapterMethod(mRecyclerTopRated, (ArrayList<Result>) resultsList,0);
            }
        });

        mActivityViewModel.getGenresMovie().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                adapterMethod(mRecyclerGenres, (ArrayList<Result>) resultsList,1);
            }
        });
    }

    // index: 0 - is for poster type layout;
    // index: 1 - is for genres type layout;
    private void adapterMethod(RecyclerView recyclerView, ArrayList<Result> list, int index) {
        MoviesAdapter adapter = new MoviesAdapter(list, getContext(), index);
        recyclerView.setAdapter(adapter);
    }
}
