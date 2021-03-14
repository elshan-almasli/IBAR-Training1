package com.anushka.tmdbclient.presenter.di.core

import android.content.Context
import com.anushka.tmdbclient.presenter.di.movie.MovieModule
import com.anushka.tmdbclient.presenter.di.movie.MovieSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context.applicationContext
    }

}