package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.movie.Movie

interface IMovieRepository {

    suspend fun getMovie(): List<Movie>?

    suspend fun updateMovie(): List<Movie>?

}