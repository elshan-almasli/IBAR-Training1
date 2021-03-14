package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface ITvShowRepository {

    suspend fun getTvShow(): List<TvShow>?

    suspend fun updateTvShow(): List<TvShow>?

}