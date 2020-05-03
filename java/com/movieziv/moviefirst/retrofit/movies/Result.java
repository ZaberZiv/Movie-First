package com.movieziv.moviefirst.retrofit.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {

    public Result() {
    }

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("name")
    @Expose
    private String genre;
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("genres")
    @Expose
    private ArrayList<Result> genres = null;

    public ArrayList<Result> getGenresList() {
        return genres;
    }

//    @SerializedName("id")
//    @Expose
//    private String idGenre;
//
//    public String getIdGenre() {
//        return idGenre;
//    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMId() {
        return id;
    }

    public void setMId(Integer id) {
        this.id = id;
    }

//    @SerializedName("name")
//    @Expose
//    private String countryName;
//    @SerializedName("popularity")
//    @Expose
//    private Double popularity;
//    @SerializedName("vote_count")
//    @Expose
//    private Integer voteCount;
//    @SerializedName("video")
//    @Expose
//    private Boolean video;
//    @SerializedName("adult")
//    @Expose
//    private Boolean adult;
//    @SerializedName("backdrop_path")
//    @Expose
//    private Object backdropPath;
//    @SerializedName("original_language")
//    @Expose
//    private String originalLanguage;
//    @SerializedName("original_title")
//    @Expose
//    private String originalTitle;
//    @SerializedName("genre_ids")
//    @Expose
//    private List<Integer> genreIds = null;

//    public String getCountryName() {
//        return countryName;
//    }
//
//    public void setCountryName(String countryName) {
//        this.countryName = countryName;
//    }

//    public Double getPopularity() {
//        return popularity;
//    }
//
//    public void setPopularity(Double popularity) {
//        this.popularity = popularity;
//    }
//
//    public Integer getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(Integer voteCount) {
//        this.voteCount = voteCount;
//    }
//
//    public Boolean getVideo() {
//        return video;
//    }
//
//    public void setVideo(Boolean video) {
//        this.video = video;
//    }
//
//    public Boolean getAdult() {
//        return adult;
//    }
//
//    public void setAdult(Boolean adult) {
//        this.adult = adult;
//    }
//
//    public Object getBackdropPath() {
//        return backdropPath;
//    }
//
//    public void setBackdropPath(Object backdropPath) {
//        this.backdropPath = backdropPath;
//    }
//
//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }
//
//    public void setOriginalLanguage(String originalLanguage) {
//        this.originalLanguage = originalLanguage;
//    }
//
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public void setOriginalTitle(String originalTitle) {
//        this.originalTitle = originalTitle;
//    }
//
//    public List<Integer> getGenreIds() {
//        return genreIds;
//    }
//
//    public void setGenreIds(List<Integer> genreIds) {
//        this.genreIds = genreIds;
//    }
}
