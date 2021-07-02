package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class Doctor(
    @PrimaryKey
    val doctorId:Int,
    val name:String, val tel:String, val spec:String, val lat:Double,
    val lng:Double, val exp:Int, val fb:String, val image:String): Serializable {
}