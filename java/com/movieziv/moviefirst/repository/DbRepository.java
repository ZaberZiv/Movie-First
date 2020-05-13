package com.movieziv.moviefirst.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.movieziv.moviefirst.database.MovieDatabase;
import com.movieziv.moviefirst.database.MoviesDao;
import com.movieziv.moviefirst.database.TableMovies;

import java.util.List;

public class DbRepository {

    private MoviesDao moviesDao;
    private LiveData<List<TableMovies>> allMovies;

    public DbRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        moviesDao = database.moviesDao();
        allMovies = moviesDao.getAllMovies();
    }

    public void insert(TableMovies movies) {
        new InsertMovieAsyncTask(moviesDao).execute(movies);
    }

    public void update(TableMovies movies) {
        new UpdateMovieAsyncTask(moviesDao).execute(movies);
    }

    public void delete(TableMovies movies) {
        new DeleteMovieAsyncTask(moviesDao).execute(movies);
    }

    public void deleteAllMovies() {
        new DeleteAllMovieAsyncTask(moviesDao).execute();
    }

    public LiveData<List<TableMovies>> getAllMovies() {
        return allMovies;
    }

    public LiveData<TableMovies> getMovie(int id) {
        return moviesDao.getMovieById(id);
    }

    private static class InsertMovieAsyncTask extends AsyncTask<TableMovies, Void, Void> {
        private MoviesDao moviesDao;

        public InsertMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(TableMovies... movies) {
            moviesDao.insert(movies[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<TableMovies, Void, Void> {
        private MoviesDao moviesDao;

        public UpdateMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(TableMovies... movies) {
            moviesDao.update(movies[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<TableMovies, Void, Void> {
        private MoviesDao moviesDao;

        public DeleteMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(TableMovies... movies) {
            moviesDao.delete(movies[0]);
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private MoviesDao moviesDao;

        public DeleteAllMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moviesDao.deleteAllMovies();
            return null;
        }
    }
}
