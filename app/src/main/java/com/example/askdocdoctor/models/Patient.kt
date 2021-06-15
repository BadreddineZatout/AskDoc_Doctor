package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Patient (
    @PrimaryKey
    val id:Int,
    val name:String, val tel:String): Serializable {
}