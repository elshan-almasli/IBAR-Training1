package com.example.section9roomdbdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subcribers::class],version = 1)
abstract class SubsriberDatabase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao


    companion object{
        @Volatile
        private var INSTANCE: SubsriberDatabase? = null

        fun getInstance(context: Context): SubsriberDatabase{
            synchronized(this){
                var insatance = INSTANCE
                if(insatance == null){
                    insatance = Room.databaseBuilder(
                        context.applicationContext,
                        SubsriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return insatance
            }
        }
    }

}