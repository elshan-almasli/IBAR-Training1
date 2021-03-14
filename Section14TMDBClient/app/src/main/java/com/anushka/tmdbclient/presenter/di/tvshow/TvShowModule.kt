package com.anushka.tmdbclient.presenter.di.tvshow

import com.anushka.tmdbclient.domain.usecase.tvshow.GetTvShowUseCase
import com.anushka.tmdbclient.domain.usecase.tvshow.UpdateTvShowUseCase
import com.anushka.tmdbclient.presenter.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowUseCase,
        updateTvShowsUseCase: UpdateTvShowUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowsUseCase,
            updateTvShowsUseCase
        )
    }

}