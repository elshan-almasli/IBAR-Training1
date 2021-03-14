package com.anushka.didemo.di

import android.util.Log
import com.anushka.didemo.MemoryCard
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val memoryCardsize: Int) {

    @Provides
    fun provideMemoryCard(): MemoryCard{
        Log.i("TAG", "provideMemoryCard: $memoryCardsize")
        return MemoryCard()
    }
}