package com.example.section9roomdbdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subscriber_table")
data class Subcribers(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="subscriber_id")
    var id: Int = 0,
    @ColumnInfo(name = "subscriber_name")
    var name: String?,
    @ColumnInfo(name = "subscriber_mail")
    var mail: String?

)