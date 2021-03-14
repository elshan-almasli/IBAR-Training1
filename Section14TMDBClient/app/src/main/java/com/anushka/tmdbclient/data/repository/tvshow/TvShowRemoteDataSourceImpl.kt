package com.anushka.tmdbclient.data.repository.tvshow

import com.anushka.tmdbclient.data.api.TMBDService
import com.anushka.tmdbclient.data.model.tvshow.TVShowList
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMBDService,
    private val apiKey:String
): TvShowRemoteDataSource {
    override suspend fun getTvShows()
            : Response<TVShowList> = tmdbService.getTVShowList(apiKey)

}

