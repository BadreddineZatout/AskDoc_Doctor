package com.example.askdoc.services

import android.content.Context
import androidx.room.Room
import com.example.askdoc.database.AppDatabase

object RoomService {
    lateinit var context: Context
    val appDatabase:AppDatabase by lazy{
        Room.databaseBuilder(context, AppDatabase::class.java, "Rendev_vous_medical").allowMainThreadQueries().build()
    }
}