package com.example.javacourcecommonapp.di;

import com.example.javacourcecommonapp.data.api.MovieDataService;
import com.example.javacourcecommonapp.data.local.MovieDao;
import com.example.javacourcecommonapp.data.repository.MovieLocalRepository;
import com.example.javacourcecommonapp.data.repository.MovieLocalRepositoryImpl;
import com.example.javacourcecommonapp.data.repository.MovieRemoteRepository;
import com.example.javacourcecommonapp.data.repository.MovieRemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MovieRemoteRepository provideMovieRepository(MovieDataService movieDataService){
        return new MovieRemoteRepositoryImpl(movieDataService);
    }

    @Provides
    @Singleton
    MovieLocalRepository provideMovieLocalRepository(MovieDao movieDao){
        return new MovieLocalRepositoryImpl(movieDao);
    }
}
