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
    val bookingId: Int?,
    val bookingDate: String?,
    val bookingHour: Int?,
    val doctorId: Int?,
    val patientId: Int?,
    val CodeQR: String?,
    val patientName: String?,
    val createdAt: String?,
    val updatedAt: String?
    )