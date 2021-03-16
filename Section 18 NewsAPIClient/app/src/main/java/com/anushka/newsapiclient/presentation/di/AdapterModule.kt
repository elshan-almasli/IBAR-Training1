package com.anushka.newsapiclient.presentation.di

import com.anushka.newsapiclient.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun provideNewsAdapter():NewsAdapter{
       return NewsAdapter()
   }
}