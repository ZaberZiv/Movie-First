package com.movieziv.moviefirst.retrofit;

import com.movieziv.moviefirst.retrofit.movies.Movies;
import com.movieziv.moviefirst.retrofit.movies.Result;
import com.movieziv.moviefirst.retrofit.trailers.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("movie/upcoming?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Movies> getUpcomingPosts(@Query("page") int page);

    @GET("movie/popular?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Movies> getPopularPosts(@Query("page") int page);

    @GET("movie/top_rated?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Movies> getTopRatedPosts(@Query("page") int page);

    @GET("movie/{movie_id}?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Movies> getMovieDetails(@Path("movie_id") int movie_id);

    @GET("search/movie?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Movies> getSearchMovies(@Query("query") String query);

    @GET("movie/{movie_id}/similar?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US&page=1")
    Call<Movies> getSimilarMovies(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Result> getResultDetails(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/videos?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-US")
    Call<Trailer> getVideo(@Path("movie_id") int movie_id);

    @GET("genre/movie/list?api_key=91924b5d2297d43f3a0a61c6e3435543&language=en-U")
    Call<Movies> getListOfGenres();

    @GET("discover/movie?api_key=91924b5d2297d43f3a0a61c6e3435543")
    Call<Movies> getMoviesByGenres(@Query("with_genres") int genre_id, @Query("page") int page);
}
