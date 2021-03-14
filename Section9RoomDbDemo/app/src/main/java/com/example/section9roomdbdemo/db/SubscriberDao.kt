package com.example.section9roomdbdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insert(subcribers: Subcribers): Long

    @Update
    suspend fun update(subcribers: Subcribers)

    @Delete
    suspend fun delete(subcribers: Subcribers)

    @Query("DELETE FROM subscriber_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_table")
    fun getAllSubscribers(): LiveData<List<Subcribers>>

}