package com.example.javacourcecommonapp.di;

import com.example.javacourcecommonapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();
}
