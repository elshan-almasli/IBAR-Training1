package com.example.javacourcecommonapp.di;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.javacourcecommonapp.data.local.MovieDao;
import com.example.javacourcecommonapp.data.local.MovieDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MovieDatabase provideDatabase(Context context) {
        return Room
                .databaseBuilder(
                        context,
                        MovieDatabase.class,
                        "movie_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(MovieDatabase movieDatabase){
        return movieDatabase.getMovieDao();
    }

}
