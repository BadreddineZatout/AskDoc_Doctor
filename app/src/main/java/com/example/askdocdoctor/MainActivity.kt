package com.example.askdocdoctor

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.askdoc.models.Doctor
import com.example.askdoc.models.Patient
import com.example.askdoc.services.RetrofitService
import com.example.askdocdoctor.models.Auth
import com.example.askdocdoctor.services.viewModelD
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val doctor = intent.getSerializableExtra("doctor") as Doctor
        val vm = ViewModelProvider(this).get(viewModelD::class.java)
        vm.doctor = doctor
        val pref = getSharedPreferences("Auth", Context.MODE_PRIVATE)
        val connected = pref.getBoolean("connected", false)
        if(connected){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(R.layout.activity_main)
            login.setOnClickListener {
                val credentials = Auth(telephone.text.toString(), pdw.text.toString())
                val call = RetrofitService.endpoint.AuthDoctor(credentials)
                call.enqueue(object: Callback<Doctor> {
                    @SuppressLint("RestrictedApi")
                    override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                        if(response?.isSuccessful){
                            val data = response.body()!!
                            pref.edit().putBoolean("connected", true).apply()
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("doctor", data)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@MainActivity, "Wrong credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                    @SuppressLint("RestrictedApi")
                    override fun onFailure(call: Call<Doctor>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}