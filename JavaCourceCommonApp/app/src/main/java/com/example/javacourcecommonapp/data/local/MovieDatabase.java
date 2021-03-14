package com.example.javacourcecommonapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.javacourcecommonapp.data.model.Movie;

@Database(entities = {Movie.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

}
