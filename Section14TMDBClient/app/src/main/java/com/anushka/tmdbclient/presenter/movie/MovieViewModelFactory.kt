package com.anushka.tmdbclient.presenter.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.domain.usecase.movie.GetMovieUseCase
import com.anushka.tmdbclient.domain.usecase.movie.UpdateMovieUseCase

class MovieViewModelFactory(
    private val getMovieUseCase: GetMovieUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMovieUseCase, updateMovieUseCase) as T
    }
}