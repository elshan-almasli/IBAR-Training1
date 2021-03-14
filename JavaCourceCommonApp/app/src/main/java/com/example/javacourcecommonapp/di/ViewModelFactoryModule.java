package com.example.javacourcecommonapp.di;

import com.example.javacourcecommonapp.data.repository.MovieLocalRepository;
import com.example.javacourcecommonapp.data.repository.MovieRemoteRepository;
import com.example.javacourcecommonapp.ui.viewmodel.MainViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {

    @Provides
    @Singleton
    MainViewModelFactory provideMainViewModel(
            MovieRemoteRepository movieRepository,
            MovieLocalRepository movieLocalRepository){
        return new MainViewModelFactory(movieRepository,movieLocalRepository);
    }

}
