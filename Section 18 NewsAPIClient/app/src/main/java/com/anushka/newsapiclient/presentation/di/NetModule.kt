package com.anushka.newsapiclient.presentation.di

import com.anushka.newsapiclient.BuildConfig
import com.anushka.newsapiclient.data.api.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .client(client)
             .baseUrl(BuildConfig.BASE_URL)
             .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):NewsAPIService{
        return retrofit.create(NewsAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLogInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

}













