package com.anushka.tmdbclient.data.repository.movie

import com.anushka.tmdbclient.data.api.TMBDService
import com.anushka.tmdbclient.data.model.movie.MovieList
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmbdService: TMBDService,
    private val apiKey: String
) :
    IMovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = tmbdService.getMoviesList(apiKey)
}