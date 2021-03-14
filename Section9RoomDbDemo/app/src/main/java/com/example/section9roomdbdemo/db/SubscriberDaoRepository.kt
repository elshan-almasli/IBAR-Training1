package com.example.section9roomdbdemo.db

import androidx.lifecycle.LiveData

class SubscriberDaoRepository(private val dao: SubscriberDao) {

    val allSubcribersData = dao.getAllSubscribers()

    suspend fun insert(subcribers: Subcribers){
        dao.insert(subcribers)
    }

    suspend fun update(subcribers: Subcribers){
        dao.update(subcribers)
    }

    suspend fun delete(subcribers: Subcribers){
        dao.delete(subcribers)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }


}