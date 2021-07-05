package com.example.askdocdoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_booking.*

class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       Singleton.viewModel = ViewModelProviders.of(activity as HomeActivity).get(viewModel::class.java)

        if (Singleton.viewModel.booking != null) {
            textView.text= Singleton.viewModel.booking!!.bookingId.toString()
            textView6.text = Singleton.viewModel.booking!!.patientName
            textView8.text = Singleton.viewModel.booking!!.bookingDate
            textView10.text = Singleton.viewModel.booking!!.bookingHour.toString()

        }
    }

}