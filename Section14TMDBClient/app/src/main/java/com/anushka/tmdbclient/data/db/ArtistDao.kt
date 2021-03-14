package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: List<Artist>)

    @Query("SELECT * FROM popular_artists")
    suspend fun selectAllArtists(): List<Artist>

    @Query("DELETE FROM popular_artists")
    suspend fun deleteAllMovies()
}