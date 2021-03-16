package com.anushka.newsapiclient.presentation.di

import com.anushka.newsapiclient.domain.repository.NewsRepository
import com.anushka.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ):GetNewsHeadlinesUseCase{
      return GetNewsHeadlinesUseCase(newsRepository)
   }
}


















