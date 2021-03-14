package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.Movie

interface IMovieLocalDatasource {

    suspend fun getMovies(): List<Movie>

    suspend fun updateMovies(artistList: List<Movie>)

    suspend fun clearDb()

}