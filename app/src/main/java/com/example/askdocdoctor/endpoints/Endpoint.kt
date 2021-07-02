package com.example.askdoc.endpoints

import com.example.askdoc.models.Doctor
import com.example.askdocdoctor.models.Auth
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("doctor/auth")
    fun AuthDoctor(@Body auth:Auth):Call<Doctor>
}