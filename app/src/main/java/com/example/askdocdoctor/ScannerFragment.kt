package com.example.askdocdoctor

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_DENIED
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.askdoc.models.Booking
import com.example.askdoc.services.RetrofitService
import kotlinx.android.synthetic.main.fragment_scanner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest


class ScannerFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var activity: Activity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.CAMERA)==
            PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),123)
        }else {
            startscanning()
            getBooking(1);
        }

    }

    private fun  getBooking(i: Int){
        val call = RetrofitService.endpoint.getBooking(i)
        call.enqueue(object : Callback<List<Booking>> {
            override fun onResponse(call: Call<List<Booking>>, response:
            Response<List<Booking>>) {

                if(response.isSuccessful) {
                    val list = response.body()
                    if (list!=null){
                        for(item in list){
                            Toast.makeText(requireActivity(), item.bookingDate,Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(requireActivity(), "erreur",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                Toast.makeText(requireActivity(), "erreur2",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun startscanning() {
        val scannerView: CodeScannerView=scanner_view
        codeScanner = CodeScanner(requireActivity(),scannerView)
        codeScanner.camera=CodeScanner.CAMERA_BACK
        codeScanner.formats=CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode= ScanMode.SINGLE
        codeScanner.isFlashEnabled = true
        codeScanner.isFlashEnabled=false
        activity = requireActivity()
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                var viewModel = ViewModelProviders.of(activity as ScannerFragment).get(viewModel::class.java)
                Toast.makeText(requireActivity(), "Scanner result ${it.text}",Toast.LENGTH_SHORT).show()
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {

                Toast.makeText(requireActivity(), "Camera error ${it.message}",Toast.LENGTH_SHORT).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==123){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireActivity(), "start scanning",Toast.LENGTH_SHORT).show()
                startscanning()
            } else {
                Toast.makeText(requireActivity(), "Camera permission denied",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized){
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized){
            codeScanner?.releaseResources()
        }
        super.onPause()

    }


}