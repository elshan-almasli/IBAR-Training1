package com.example.javacourcecommonapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import com.example.javacourcecommonapp.data.model.Movie;

public interface MovieRemoteRepository {

    MutableLiveData<List<Movie>> getMovieList();
}
