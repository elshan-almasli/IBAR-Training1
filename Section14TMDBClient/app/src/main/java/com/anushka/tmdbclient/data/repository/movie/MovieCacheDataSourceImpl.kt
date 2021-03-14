package com.anushka.tmdbclient.data.repository.movie

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieCashDataSource

class MovieCacheDataSourceImpl : IMovieCashDataSource {

    private var movieList = arrayListOf<Movie>()

    override suspend fun getMovieCashData(): List<Movie> {
        return movieList
    }

    override suspend fun updateMovieCashData(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}