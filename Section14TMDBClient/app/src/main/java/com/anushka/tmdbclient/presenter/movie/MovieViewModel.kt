package com.anushka.tmdbclient.presenter.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.domain.usecase.movie.GetMovieUseCase
import com.anushka.tmdbclient.domain.usecase.movie.UpdateMovieUseCase

class MovieViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {

    fun getMovie() = liveData{
        val movieList = getMovieUseCase.execute()
        emit(movieList)
    }

    fun updateMovie() = liveData {
        val moveList = updateMovieUseCase.execute()
        emit(moveList)
    }
}