package com.example.askdoc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.askdoc.models.*

@Database(entities = arrayOf(Doctor::class, Patient::class, Booking::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
}