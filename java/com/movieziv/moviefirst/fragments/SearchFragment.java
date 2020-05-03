package com.movieziv.moviefirst.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.adapter.MoviesAdapter;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.viewmodel.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private EditText mSearchEditText;
    private DetailsViewModel mModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mRecyclerView = view.findViewById(R.id.frag_search_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager
                (getActivity(), 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mSearchEditText = view.findViewById(R.id.frag_search_edit_field);
        TextView search_button = view.findViewById(R.id.frag_search_button);
        search_button.setOnClickListener(this);

        mModel = new ViewModelProvider(this)
                .get(DetailsViewModel.class);

        String movie = "Terminator";
        getResearch(movie);

        return view;
    }

    @Override
    public void onClick(View v) {
        String movie = mSearchEditText.getText().toString();
        getResearch(movie);
    }

    private void getResearch(String movieName) {
        mModel.getSearchMovie(movieName).observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultslist) {
                adapterMethod(mRecyclerView, (ArrayList<Result>) resultslist,0);
            }
        });
    }

    private void adapterMethod(RecyclerView recyclerView, ArrayList<Result> list, int index) {
        MoviesAdapter adapter = new MoviesAdapter(list, getContext(), index);
        recyclerView.setAdapter(adapter);
    }
}
