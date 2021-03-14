package com.anushka.tmdbclient.data.repository.artist.datasourceImpl

import com.anushka.tmdbclient.data.api.TMBDService
import com.anushka.tmdbclient.data.model.artist.ArtistList
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMBDService,
    private val apiKey:String
): ArtistRemoteDataSource {
    override suspend fun getArtists()
            : Response<ArtistList> = tmdbService.getPopularPeople(apiKey)

}

