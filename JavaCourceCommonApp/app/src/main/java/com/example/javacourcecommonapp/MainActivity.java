package com.example.javacourcecommonapp;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.ui.viewmodel.MainViewModel;
import com.example.javacourcecommonapp.ui.viewmodel.MainViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    MainViewModelFactory factory;

    public MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this,factory).get(MainViewModel.class);

//        mainViewModel.getData();

        mainViewModel.getData().observe(this, movies -> {
            Log.i(TAG, "onCreate: " + movies.get(0).getTitle());
            mainViewModel.setData(movies);
        });

//        mainViewModel.getMovieList().observe(this,movies -> {
//            Log.i(TAG, "onCreate: " + movies.toString());
//        });
    }
}