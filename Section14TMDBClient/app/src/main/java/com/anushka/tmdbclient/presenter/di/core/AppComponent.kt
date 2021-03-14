package com.anushka.tmdbclient.presenter.di.core

import com.anushka.tmdbclient.presenter.di.artist.ArtistSubComponent
import com.anushka.tmdbclient.presenter.di.movie.MovieSubComponent
import com.anushka.tmdbclient.presenter.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CacheDataSourceModule::class,
        DataSourceModule::class,
        DbModule::class,
        LocalDataSourceModule::class,
        MoviRepositoryModule::class,
        NetModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    fun movieSubComponent(): MovieSubComponent.Factory

    fun artistSubComponent(): ArtistSubComponent.Factory

    fun tvShowSubComponent(): TvShowSubComponent.Factory

}