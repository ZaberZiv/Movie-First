package com.zivapp.kotlinmoviefirst.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zivapp.kotlinmoviefirst.pojo.genre.Genres
import com.zivapp.kotlinmoviefirst.pojo.movies.PopularMovies
import com.zivapp.kotlinmoviefirst.pojo.movies.TopRatedMovies
import com.zivapp.kotlinmoviefirst.pojo.movies.UpcomingMovies

@Database(entities =
[UpcomingMovies::class,
    PopularMovies::class,
    TopRatedMovies::class,
    Genres::class],
        version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun upcomingMovieInfoDao(): UpcomingMovieInfoDao
    abstract fun popularMovieInfoDao(): PopularMovieDao
    abstract fun topRatedMovieInfoDao(): TopRatedMovieDao
    abstract fun genresMovieInfoDao(): GenresMovieDao
}