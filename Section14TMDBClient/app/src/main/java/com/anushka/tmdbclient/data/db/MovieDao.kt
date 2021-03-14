package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<Movie>)

    @Query("SELECT * FROM popular_movies")
    suspend fun selectAllMovies(): List<Movie>

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies()

}