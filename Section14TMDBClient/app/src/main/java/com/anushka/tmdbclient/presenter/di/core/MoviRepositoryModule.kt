package com.anushka.tmdbclient.presenter.di.core

import com.anushka.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.data.repository.movie.MovieCacheDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.MovieLocalDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.MovieRemoteDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieCashDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieLocalDatasource
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieRemoteDataSource
import com.anushka.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.anushka.tmdbclient.domain.repository.IArtistRepository
import com.anushka.tmdbclient.domain.repository.IMovieRepository
import com.anushka.tmdbclient.domain.repository.ITvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviRepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        iMovieCashDataSource: IMovieCashDataSource,
        iMovieLocalDatasource: IMovieLocalDatasource,
        iMovieRemoteDataSource: IMovieRemoteDataSource
    ): IMovieRepository {
        return MovieRepositoryImpl(
            iMovieLocalDatasource,
            iMovieCashDataSource,
            iMovieRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistRemoteDatasource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): IArtistRepository {

        return ArtistRepositoryImpl(
            artistRemoteDatasource,
            artistLocalDataSource,
            artistCacheDataSource
        )


    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): ITvShowRepository {

        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )


    }

}