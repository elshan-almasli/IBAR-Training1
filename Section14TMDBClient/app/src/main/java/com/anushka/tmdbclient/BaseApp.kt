package com.anushka.tmdbclient

import android.app.Application
import com.anushka.tmdbclient.presenter.di.Injector
import com.anushka.tmdbclient.presenter.di.artist.ArtistSubComponent
import com.anushka.tmdbclient.presenter.di.core.*
import com.anushka.tmdbclient.presenter.di.movie.MovieSubComponent
import com.anushka.tmdbclient.presenter.di.tvshow.TvShowSubComponent

class BaseApp: Application(), Injector {

    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .dataSourceModule(DataSourceModule(BuildConfig.API_KEY))
            .build()

    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }

    override fun createTVSHOWSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }
}