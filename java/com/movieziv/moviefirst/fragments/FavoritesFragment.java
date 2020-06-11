package com.movieziv.moviefirst.fragments;

import android.os.Bundle;
import android.util.Log;
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

import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.adapter.MoviesAdapter;
import com.movieziv.moviefirst.database.TableMovies;
import com.movieziv.moviefirst.eventbus.ResultEventBus;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.viewmodel.DbViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DbViewModel mDbViewModel;
    private static ArrayList<Result> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mDbViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(DbViewModel.class);

        mRecyclerView = view.findViewById(R.id.frag_favorites_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(
                getContext(),
                1,
                RecyclerView.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);

        mDbViewModel.getMovies().observe(this, new Observer<List<TableMovies>>() {
            @Override
            public void onChanged(List<TableMovies> tableMovies) {
                mDbViewModel.getResultObject(tableMovies);
            }
        });
        return view;
    }

    @Subscribe
    public void onEvent(ResultEventBus bus) {
        mList.add(bus.result);
        MoviesAdapter adapter = new MoviesAdapter(mList, getContext(), 2);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mList.clear();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
