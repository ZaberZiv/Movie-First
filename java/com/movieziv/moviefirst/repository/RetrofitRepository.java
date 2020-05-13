package com.movieziv.moviefirst.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movieziv.moviefirst.activities.ShowAllMoviesActivity;
import com.movieziv.moviefirst.database.MovieDatabase;
import com.movieziv.moviefirst.database.MoviesDao;
import com.movieziv.moviefirst.database.TableMovies;
import com.movieziv.moviefirst.eventbus.ResultEventBus;
import com.movieziv.moviefirst.retrofit.JSONPlaceHolderApi;
import com.movieziv.moviefirst.retrofit.RetrofitInstance;
import com.movieziv.moviefirst.retrofit.movies.Movies;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.retrofit.trailers.MediaVideo;
import com.movieziv.moviefirst.retrofit.trailers.Trailer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movieziv.moviefirst.activities.MovieDetailActivity.TRAILER_URL;

public class RetrofitRepository {

    private MutableLiveData<List<Result>> mutableLiveData1 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData2 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData3 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData4 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData5 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData6 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData7 = new MutableLiveData<>();
    private MutableLiveData<Movies> mutableLiveData8 = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveData9 = new MutableLiveData<>();
    private MutableLiveData<String> mutableLiveDataString = new MutableLiveData<>();

    private Application application;
    private MoviesDao moviesDao;

    public RetrofitRepository() {
    }

    public RetrofitRepository(Application application) {
        this.application = application;
        MovieDatabase database = MovieDatabase.getInstance(application);
        moviesDao = database.moviesDao();
    }

    public MutableLiveData<List<Result>> getUpcomingLiveData() {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getUpcomingPosts(1);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData1.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData1;
    }

    public MutableLiveData<List<Result>> getPopularLiveData() {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getPopularPosts(1);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData2.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData2;
    }

    public MutableLiveData<List<Result>> getTopRatedLiveData() {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getTopRatedPosts(1);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData3.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData3;
    }

    public MutableLiveData<List<Result>> getGenreLiveData() {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getListOfGenres();

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getGenres();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData4.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData4;
    }

    public MutableLiveData<List<Result>> getSimilarMoviesLiveData(int id) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getSimilarMovies(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData5.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData5;
    }

    public MutableLiveData<List<Result>> getSearchMoviesLiveData(String movieName) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getSearchMovies(movieName);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData6.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData6;
    }

    public MutableLiveData<List<Result>> getAllMoviesLiveData(int currentPage, int genreId) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = null;

        switch (ShowAllMoviesActivity.mFlag) {
            case 1:
                call = api.getUpcomingPosts(currentPage);
                break;
            case 2:
                call = api.getPopularPosts(currentPage);
                break;
            case 3:
                call = api.getTopRatedPosts(currentPage);
                break;
            case 4:
                call = api.getMoviesByGenres(genreId, currentPage);
                break;
        }

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Movies posts = response.body();
                ArrayList<Result> moviesList;
                try {
                    moviesList = posts.getResults();
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesList = null;
                }
                mutableLiveData7.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData7;
    }

    public MutableLiveData<Movies> getGenreAndBudgetLiveData(int id) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Movies> call = api.getMovieDetails(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "" + response.code());
                    return;
                }
                Movies posts = response.body();
                mutableLiveData8.setValue(posts);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveData8;
    }

    public void getListOfIds(List<TableMovies> list) {
        for (TableMovies l : list) {
            getMovieDetailsLiveData(l.getMovie_id());
        }
    }

    public void getMovieDetailsLiveData(int id) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Result> call = api.getResultDetails(id);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "RESPONSE CODE: " + response.code());
                    return;
                }
                Result posts = response.body();
                EventBus.getDefault().post(new ResultEventBus(posts));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public MutableLiveData<String> getVideoLiveData(int id) {
        JSONPlaceHolderApi api = RetrofitInstance.getService();
        Call<Trailer> call = api.getVideo(id);

        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "" + response.code());
                    return;
                }

                Trailer posts = response.body();
                ArrayList<MediaVideo> movieVideoList = posts.getResults();
                String videoPath = "";
                if (movieVideoList != null) {
                    try {
                        String keyYouTube = movieVideoList.get(0).getKey();
                        videoPath = TRAILER_URL + keyYouTube;
                    } catch (Exception e) {
                        e.printStackTrace();
                        videoPath = "0";
                    }
                }
                mutableLiveDataString.setValue(videoPath);
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                Log.i("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return mutableLiveDataString;
    }
}
