package com.anushka.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.model.tvshow.TvShow

@Database(
    entities = [
        Artist::class,
        Movie::class,
        TvShow::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistDao

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}