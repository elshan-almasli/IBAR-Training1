package com.anushka.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anushka.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShow(tvShow: List<TvShow>)

    @Query("Select*from popular_tvShows")
    suspend fun selectAllTVShows(): List<TvShow>

    @Query("Delete from popular_tvShows")
    suspend fun deleteAllTVShows()
}