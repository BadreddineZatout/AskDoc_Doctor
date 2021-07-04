package com.example.askdoc.endpoints

import com.example.askdoc.models.Booking
import com.example.askdoc.models.Doctor
import com.example.askdocdoctor.models.Auth
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("doctor/auth")
    fun AuthDoctor(@Body auth:Auth):Call<Doctor>

    @GET("booking/{doctorId}")
    fun getBooking(@Path("doctorId") doctorId:Int):Call<List<Booking>>

    @GET("booking/QR/{qr_code}")
    fun getBookingQR(@Path("qr_code") qr_code:String):Call<Booking>
}