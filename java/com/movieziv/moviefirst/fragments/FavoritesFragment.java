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
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.viewmodel.DbViewModel;
import com.movieziv.moviefirst.viewmodel.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DbViewModel mDbViewModel;
    private DetailsViewModel mDetailsViewModel;
    private ArrayList<Result> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mDetailsViewModel = new ViewModelProvider(this)
                .get(DetailsViewModel.class);

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

        mList.clear();
        load();

        return view;
    }

    private void load() {
        mDbViewModel.getMovies().observe(this, new Observer<List<TableMovies>>() {
            @Override
            public void onChanged(List<TableMovies> tableMovies) {
                loadFromDatabase((ArrayList<TableMovies>) tableMovies);
            }
        });
    }

    private void loadFromDatabase(ArrayList<TableMovies> list) {
        for (int i = 0; i < list.size(); i++) {
            mDetailsViewModel.getListOfMovieDetails(list.get(i).getMovie_id()).observe(this, new Observer<Result>() {
                @Override
                public void onChanged(Result result) {

                    mList.add(result);

                    for (int i = 0; i < mList.size() - 1; i++) {
                        for (int k = mList.size() - 1; k > i; k--) {
                            if (mList.get(i).equals(mList.get(k))) {
                                mList.remove(mList.get(k));
                            }
                        }
                    }

                    if (mList != null) {
                        MoviesAdapter adapter = new MoviesAdapter(mList, getContext(), 2);
                        mRecyclerView.setAdapter(adapter);
                    } else {
                        Log.i("onChanged", "mList is null");
                    }
                }
            });
        }
    }
}
