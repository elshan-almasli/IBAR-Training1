package com.example.javacourcecommonapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javacourcecommonapp.data.model.Movie;

import java.util.List;

public interface MovieLocalRepository {

    void insertMovie(List<Movie> movies);

    void updateMovie(Movie movie);

    void deleteMovie(Movie movie);

    void deleteAll();

    LiveData<List<Movie>> getAllMovies();


}
