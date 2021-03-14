package com.anushka.tmdbclient.presenter.di.movie

import com.anushka.tmdbclient.domain.usecase.movie.GetMovieUseCase
import com.anushka.tmdbclient.domain.usecase.movie.UpdateMovieUseCase
import com.anushka.tmdbclient.presenter.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMovieUseCase: GetMovieUseCase,
        updateMovieUseCase: UpdateMovieUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMovieUseCase, updateMovieUseCase)
    }
}