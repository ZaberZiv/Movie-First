package com.movieziv.moviefirst.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.movieziv.moviefirst.R;
import com.movieziv.moviefirst.fragments.FavoritesFragment;
import com.movieziv.moviefirst.fragments.MoviesFragment;
import com.movieziv.moviefirst.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    boolean flag_movies, flag_search, flag_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag_movies = false;
        flag_search = true;
        flag_fav = true;

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MoviesFragment()).commit();
    }

    public void seeAllMovies(View view) {
        int flag = 0;
        String menu_name = "All movies";

        switch (view.getId()) {
            case R.id.frag_movies_see_all_btn1:
                flag = 1;
                menu_name = "Upcoming";
                break;
            case R.id.frag_movies_see_all_btn2:
                flag = 2;
                menu_name = "Popular";
                break;
            case R.id.frag_movies_see_all_btn3:
                menu_name = "Top Rated";
                flag = 3;
                break;
        }

        Intent intent = new Intent(this, ShowAllMoviesActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("menu", menu_name);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_movies:
                            if (flag_movies) {
                                flag_fav = true;
                                flag_search = true;
                                flag_movies = false;
                                selectedFragment = new MoviesFragment();
                            }
                            break;
                        case R.id.nav_search:
                            if (flag_search) {
                                flag_movies = true;
                                flag_fav = true;
                                flag_search = false;
                                selectedFragment = new SearchFragment();
                            }
                            break;
                        case R.id.nav_favorites:
                            if (flag_fav) {
                                flag_movies = true;
                                flag_search = true;
                                flag_fav = false;
                                selectedFragment = new FavoritesFragment();
                            }
                            break;
                    }

                    try {
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_container, selectedFragment).
                                commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            };
}
