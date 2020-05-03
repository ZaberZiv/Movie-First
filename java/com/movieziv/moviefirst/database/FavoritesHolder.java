package com.movieziv.moviefirst.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ImageView;

import com.movieziv.moviefirst.R;

public class FavoritesHolder {

    public void addToFav(int movie_ID, int active_flag, ImageView starImage) {
        if (active_flag == 0) {
            starImage.setImageResource(R.drawable.ic_star_black_24dp);
            active_flag = 1;
            saveToDatabase(movie_ID, active_flag);
        } else {
            starImage.setImageResource(R.drawable.ic_star_border_black_24dp);
            active_flag = 0;
            removeFromDatabase(movie_ID, active_flag);
        }
    }

    private void saveToDatabase(int movie_ID, int active_flag) {


        Log.i("saveToDatabase", "data Saved! Flag: " + active_flag + ", ID: " + movie_ID);
    }

    public int loadFromDatabase(int movie_ID, ImageView starImage) {



        int active_flag = 0;

            Log.i("DB", "There is flag: " + active_flag);

        if (active_flag == 1) {
            starImage.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            starImage.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
        Log.i("loadFromDatabase", "Current Data! Flag: " + active_flag + ", ID: " + movie_ID);

        return active_flag;
    }

    private void removeFromDatabase(int movie_ID, int active_flag) {

        Log.i("removeFromDatabase", "data Removed! Flag: " + active_flag + ", ID: " + movie_ID);
    }
}
