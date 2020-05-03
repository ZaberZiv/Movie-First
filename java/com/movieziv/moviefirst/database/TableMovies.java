package com.movieziv.moviefirst.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class TableMovies {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_id")
    private int movie_id;

    @ColumnInfo(name = "fav_flag")
    private int fav_flag;

    @Ignore
    public TableMovies(){}

    public TableMovies(int movie_id, int fav_flag) {
        this.movie_id = movie_id;
        this.fav_flag = fav_flag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setFav_flag(int fav_flag) {
        this.fav_flag = fav_flag;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public int getFav_flag() {
        return fav_flag;
    }
}
