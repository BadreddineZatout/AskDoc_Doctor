package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*
@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Doctor::class, parentColumns = arrayOf("doctorId"),
    childColumns = arrayOf("doctorId"), onDelete = ForeignKey.CASCADE)
))
data class Booking (
    @PrimaryKey
    val bookingId:Long,
    val bookingDate: Date?,
    val bookingTime: String,
    val doctorId:Long)
{
}