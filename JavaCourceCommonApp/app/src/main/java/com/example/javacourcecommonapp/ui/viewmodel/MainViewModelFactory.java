package com.example.javacourcecommonapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.javacourcecommonapp.data.repository.MovieLocalRepository;
import com.example.javacourcecommonapp.data.repository.MovieRemoteRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private MovieRemoteRepository movieRepository;
    private MovieLocalRepository movieLocalRepository;

    public MainViewModelFactory(MovieRemoteRepository movieRepository, MovieLocalRepository movieLocalRepository){
        this.movieRepository = movieRepository;
        this.movieLocalRepository = movieLocalRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(movieRepository,movieLocalRepository);
    }
}
