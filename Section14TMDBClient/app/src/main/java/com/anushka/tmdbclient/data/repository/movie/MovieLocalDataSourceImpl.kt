package com.anushka.tmdbclient.data.repository.movie

import com.anushka.tmdbclient.data.db.MovieDao
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : IMovieLocalDatasource {

    override suspend fun getMovies(): List<Movie> = movieDao.selectAllMovies()

    override suspend fun updateMovies(artistList: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(artistList)
        }
    }

    override suspend fun clearDb() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}