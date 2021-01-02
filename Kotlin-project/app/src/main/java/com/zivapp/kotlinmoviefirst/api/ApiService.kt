package com.zivapp.kotlinmoviefirst.api

import com.zivapp.kotlinmoviefirst.pojo.Datum
import com.zivapp.kotlinmoviefirst.pojo.video.Trailer
import com.zivapp.kotlinmoviefirst.pojo.movies.Movie
import com.zivapp.kotlinmoviefirst.pojo.movies.MovieInfo

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val API_KEY = "91924b5d2297d43f3a0a61c6e3435543"

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SEARCH = "query"
        private const val QUERY_PARAM_GENRES = "with_genres"

        private const val PATH_PARAM_MOVIE_ID = "movie_id"
    }

    @GET("movie/upcoming?")
    fun getUpcomingPosts(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_PAGE) page: Int
    ): Single<Datum>

    @GET("movie/popular?")
    fun getPopularPosts(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_PAGE) page: Int
    ): Single<Datum>

    @GET("movie/top_rated?")
    fun getTopRatedPosts(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_PAGE) page: Int
    ): Single<Datum>

    @GET("search/movie?")
    fun getSearchMovies(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_SEARCH) query: String
    ): Single<Datum>

    @GET("movie/{movie_id}/similar?")
    fun getSimilarMovies(
            @Path(PATH_PARAM_MOVIE_ID) movie_id: Int,
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
    ): Single<Datum>

    @GET("movie/{movie_id}?")
    fun getResultDetails(
            @Path(PATH_PARAM_MOVIE_ID) movie_id: Int,
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Single<MovieInfo>

    @GET("movie/{movie_id}/videos?")
    fun getVideo(
            @Path(PATH_PARAM_MOVIE_ID) movie_id: Int,
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Single<Trailer>

    @GET("genre/movie/list?")
    fun getListOfGenres(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
    ): Single<Datum>

    @GET("discover/movie?")
    fun getMoviesByGenres(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_GENRES) genre_id: Int,
            @Query(QUERY_PARAM_PAGE) page: Int
    ): Single<Datum>
}