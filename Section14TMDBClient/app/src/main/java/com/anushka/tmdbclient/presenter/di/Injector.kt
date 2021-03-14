package com.anushka.tmdbclient.presenter.di

import com.anushka.tmdbclient.presenter.di.artist.ArtistSubComponent
import com.anushka.tmdbclient.presenter.di.movie.MovieSubComponent
import com.anushka.tmdbclient.presenter.di.tvshow.TvShowSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent

    fun createArtistSubComponent(): ArtistSubComponent

    fun createTVSHOWSubComponent(): TvShowSubComponent

}