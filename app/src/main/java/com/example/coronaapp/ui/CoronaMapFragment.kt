package com.example.coronaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.example.coronaapp.R
import com.example.coronaapp.model.CoronaFactory
import com.example.coronaapp.vm.CoronaViewModel

/**
 * A simple [Fragment] subclass.
 */
class CoronaMapFragment : Fragment() {
    val viewModel : CoronaViewModel by activityViewModels(factoryProducer =
    {
        CoronaFactory(requireContext())
    })
    lateinit var myMapFragment: MyMapFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_corona_map, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val fragmentManager: FragmentManager = parentFragmentManager
//        var test : String = "not null"
//        if(fragmentManager == null) test = "null"
        Log.d("AppLog","1")
        myMapFragment = MyMapFragment()
//        viewModel.mapCountry.observe(viewLifecycleOwner, Observer {
//            myMapFragment.addMarker(it)
//        })
    }
}
