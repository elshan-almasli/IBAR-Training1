package com.example.section9roomdbdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.section9roomdbdemo.db.SubscriberDaoRepository
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class MainViewModelProvider(val daoRepository: SubscriberDaoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(daoRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}