package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TVShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
   suspend fun getTvShows(): Response<TVShowList>
}