package com.movieziv.moviefirst.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = TableMovies.class, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MoviesDao moviesDao();

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

//    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase database) {
//            super.onCreate(database);
//        }
//    };
//
//    private static class PopulatedDbAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private MoviesDao moviesDao;
//        private PopulatedDbAsyncTask(MovieDatabase database) {
//            moviesDao = database.moviesDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            return null;
//        }
//    }
}
