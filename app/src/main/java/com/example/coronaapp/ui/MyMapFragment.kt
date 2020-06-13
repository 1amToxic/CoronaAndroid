package com.example.coronaapp.ui

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.coronaapp.model.CoronaFactory
import com.example.coronaapp.model.CoronaUseDataK
import com.example.coronaapp.vm.CoronaViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MyMapFragment : SupportMapFragment(), OnMapReadyCallback {
    val viewModel : CoronaViewModel by activityViewModels(factoryProducer = {
        CoronaFactory(requireContext())
    })
    lateinit var country: CoronaUseDataK
    lateinit var googleMap : GoogleMap
    init {
        getMapAsync(this)
    }
    fun addMarker(country : CoronaUseDataK){
        getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d("AppLog","Ready")
        this.googleMap = p0!!
        val vietnam = LatLng(14.0583,108.2772)
        this.googleMap.addMarker(MarkerOptions().position(vietnam).title("Viet Nam"))
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam))
        this.googleMap.setOnMapClickListener {
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            markerOptions.title(it.latitude.toString()+":"+it.longitude)
            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it,10f))
            googleMap.addMarker(markerOptions)

        }
        viewModel.mapCountry.observe(viewLifecycleOwner, Observer {
            country = it
        })
        val focusCountry : LatLng = LatLng(country.lat!!.toDouble(),country.long!!.toDouble())
        this.googleMap.addMarker(MarkerOptions().position(focusCountry).title(country.country))
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(focusCountry))
    }

}