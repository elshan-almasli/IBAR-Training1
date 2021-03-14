package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.Movie

interface IMovieCashDataSource {

    suspend fun getMovieCashData(): List<Movie>

    suspend fun updateMovieCashData(movies: List<Movie>)

}