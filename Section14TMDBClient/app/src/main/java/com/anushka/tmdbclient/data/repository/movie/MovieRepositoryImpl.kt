package com.anushka.tmdbclient.data.repository.movie

import android.util.Log
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieCashDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieLocalDatasource
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieRemoteDataSource
import com.anushka.tmdbclient.domain.repository.IMovieRepository

class MovieRepositoryImpl(
    private val iMovieLocalDatasource: IMovieLocalDatasource,
    private val iMovieCashDataSource: IMovieCashDataSource,
    private val iMovieRemoteDataSource: IMovieRemoteDataSource
) : IMovieRepository {

    companion object {
        private const val TAG = "MovieRepositoryImpl"
    }

    override suspend fun getMovie(): List<Movie>? = getMovieFromCache()

    override suspend fun updateMovie(): List<Movie>? {
        val newListOfMovie = getMoviesFromApi()
        iMovieLocalDatasource.clearDb()
        iMovieLocalDatasource.updateMovies(newListOfMovie)
        iMovieCashDataSource.updateMovieCashData(newListOfMovie)
        return newListOfMovie
    }

    private suspend fun getMoviesFromApi(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            val response = iMovieRemoteDataSource.getMovies()
            val body = response?.body()
            if(body != null){
                movieList = body.movies
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return movieList
    }

    private suspend fun getMovieFromLocal(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = iMovieLocalDatasource.getMovies()

        }catch (e:Exception){
            e.printStackTrace()
        }

        if(movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMoviesFromApi()
            iMovieLocalDatasource.updateMovies(movieList)
        }
        return movieList
    }

    private suspend fun getMovieFromCache(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = iMovieCashDataSource.getMovieCashData()
        }catch (e:Exception){
            Log.i(TAG, "getMovieFromCache: ")
        }

        if(movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMovieFromLocal()
            iMovieCashDataSource.updateMovieCashData(movieList)
        }

        return movieList
    }
}