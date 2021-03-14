package com.example.javacourcecommonapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.data.repository.MovieLocalRepository;
import com.example.javacourcecommonapp.data.repository.MovieRemoteRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";
    private MovieRemoteRepository movieRepository;
    private MovieLocalRepository movieLocalRepository;
    private MutableLiveData<List<Movie>> _movieDataFromApi;
    private Executor executor;
    private List<Movie> _movie;

    public MainViewModel(MovieRemoteRepository movieRepository, MovieLocalRepository movieLocalRepository){
        this.movieRepository = movieRepository;
        this.movieLocalRepository = movieLocalRepository;
        _movieDataFromApi = new MutableLiveData<>();
//        getData();
    }

    public MutableLiveData<List<Movie>> getData(){
        return movieRepository.getMovieList();
    }

    public void setData(List<Movie> movies){
        executor = Executors.newSingleThreadExecutor();
        Log.i(TAG, "setData: " + movies.get(0).getTitle());
        executor.execute(() -> {
            Log.i(TAG, "run: ");
            movieLocalRepository.insertMovie(movies);
        });
    }

    public LiveData<List<Movie>> getMovieList(){

        return movieLocalRepository.getAllMovies();
    }

}
