package com.example.javacourcecommonapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.javacourcecommonapp.BuildConfig;
import com.example.javacourcecommonapp.data.api.MovieDataService;
import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.data.model.MovieDBResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteRepositoryImpl implements MovieRemoteRepository {

    private MovieDataService movieDataService;
    private ArrayList<Movie> movies ;
    private MutableLiveData<List<Movie>> mutableMovieLiveData ;

    public MovieRemoteRepositoryImpl(MovieDataService movieDataService){
        this.movieDataService = movieDataService;
        movies = new ArrayList<>();
        mutableMovieLiveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<Movie>> getMovieList() {

        Call<MovieDBResponse> call = movieDataService.getPopularMovies(BuildConfig.API_KEY);

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();

                if(movieDBResponse != null && movieDBResponse.getMovies() != null){
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableMovieLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

        return mutableMovieLiveData;
    }
}
