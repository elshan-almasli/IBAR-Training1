package com.anushka.newsapiclient.presentation.di

import android.app.Application
import com.anushka.newsapiclient.data.api.NewsAPIService
import com.anushka.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.anushka.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun provideNewsViewModelFactory(
     application: Application,
     getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
     newsAPIService: NewsAPIService
  ):NewsViewModelFactory{
      return NewsViewModelFactory(
          application,
          getNewsHeadlinesUseCase,
          newsAPIService
      )
  }
}








