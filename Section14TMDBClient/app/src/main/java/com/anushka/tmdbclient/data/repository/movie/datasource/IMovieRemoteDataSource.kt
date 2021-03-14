package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.db.MovieDao
import com.anushka.tmdbclient.data.model.artist.ArtistList
import com.anushka.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface IMovieRemoteDataSource {

    suspend fun getMovies(): Response<MovieList>?

}