package com.example.javacourcecommonapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javacourcecommonapp.data.local.MovieDao;
import com.example.javacourcecommonapp.data.model.Movie;

import java.util.List;

public class MovieLocalRepositoryImpl implements MovieLocalRepository{

    private MovieDao movieDao;

    public MovieLocalRepositoryImpl(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    public void insertMovie(List<Movie> movies) {
        movieDao.insertMovies(movies);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieDao.deleteMovie(movie);
    }

    @Override
    public void deleteAll() {
        movieDao.deleteAll();
    }

    @Override
    public LiveData<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies();
    }
}
