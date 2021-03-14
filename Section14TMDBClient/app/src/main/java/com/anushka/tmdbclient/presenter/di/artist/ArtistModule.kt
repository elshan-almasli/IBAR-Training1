package com.anushka.tmdbclient.presenter.di.artist

import com.anushka.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.anushka.tmdbclient.domain.usecase.artist.UpdateArtistUseCase
import com.anushka.tmdbclient.presenter.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistUseCase,
        updateArtistsUseCase: UpdateArtistUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            getArtistsUseCase,
            updateArtistsUseCase
        )
    }

}