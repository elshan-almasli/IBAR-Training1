package com.anushka.tmdbclient.presenter.di.core

import com.anushka.tmdbclient.domain.repository.IArtistRepository
import com.anushka.tmdbclient.domain.repository.IMovieRepository
import com.anushka.tmdbclient.domain.repository.ITvShowRepository
import com.anushka.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.anushka.tmdbclient.domain.usecase.artist.UpdateArtistUseCase
import com.anushka.tmdbclient.domain.usecase.movie.GetMovieUseCase
import com.anushka.tmdbclient.domain.usecase.movie.UpdateMovieUseCase
import com.anushka.tmdbclient.domain.usecase.tvshow.GetTvShowUseCase
import com.anushka.tmdbclient.domain.usecase.tvshow.UpdateTvShowUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(iMovieRepository: IMovieRepository): GetMovieUseCase{
        return GetMovieUseCase(iMovieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(iMovieRepository: IMovieRepository): UpdateMovieUseCase{
        return UpdateMovieUseCase(iMovieRepository)
    }

    @Provides
    fun provideGetArtistUseCase(artistRepository: IArtistRepository): GetArtistUseCase {
        return GetArtistUseCase(artistRepository)
    }
    @Provides
    fun provideUpdateArtistUseCase(artistRepository: IArtistRepository): UpdateArtistUseCase {
        return UpdateArtistUseCase(artistRepository)
    }

    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: ITvShowRepository): GetTvShowUseCase {
        return GetTvShowUseCase(tvShowRepository)
    }
    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: ITvShowRepository): UpdateTvShowUseCase {
        return UpdateTvShowUseCase(tvShowRepository)
    }

}