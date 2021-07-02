package com.example.askdoc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.askdoc.models.*
import com.example.askdocdoctor.database.Converter

@Database(entities = arrayOf(Doctor::class, Patient::class, Booking::class), version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){
}