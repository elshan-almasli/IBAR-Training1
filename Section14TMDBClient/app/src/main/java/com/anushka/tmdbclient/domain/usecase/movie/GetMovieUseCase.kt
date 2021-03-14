package com.anushka.tmdbclient.domain.usecase.movie

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.domain.repository.IMovieRepository

class GetMovieUseCase(private val movieRepository: IMovieRepository) {

    suspend fun execute(): List<Movie>? = movieRepository.getMovie()

}