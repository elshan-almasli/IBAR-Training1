package com.anushka.tmdbclient.presenter.di.core

import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.MovieCacheDataSourceImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.IMovieCashDataSource
import com.anushka.tmdbclient.data.repository.tvshow.TvShowCacheDataSourceImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {

    @Singleton
    @Provides
    fun provideCacheDataSource(): IMovieCashDataSource{
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource(): ArtistCacheDataSource{
        return ArtistCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTVShowCacheDataSource(): TvShowCacheDataSource{
        return TvShowCacheDataSourceImpl()
    }

}