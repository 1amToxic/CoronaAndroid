package com.example.coronaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.coronaapp.R
import com.example.coronaapp.model.CoronaFactory
import com.example.coronaapp.model.CoronaUseData
import com.example.coronaapp.vm.CoronaViewModel

/**
 * A simple [Fragment] subclass.
 */
class AllCountryFragment : Fragment() {
    lateinit var recyclerCorona : RecyclerView
    lateinit var adapter : CoronaAdapter
    lateinit var loadingView : RelativeLayout
    val viewModel : CoronaViewModel by activityViewModels(factoryProducer = {
        CoronaFactory(requireContext())
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_country, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.listCorona.observe( viewLifecycleOwner, Observer {
            Log.d("AppLogAll","${it.size}")
            adapter.submitList(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loadingView.visibility = if(it) View.VISIBLE else View.INVISIBLE
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCorona = view.findViewById(R.id.recy_corona)
        adapter = CoronaAdapter(view.context,onClickItem = {t -> navigateToDetails(t)})
        loadingView = view.findViewById(R.id.loading_view)

        recyclerCorona.apply {
            adapter = this@AllCountryFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }
    private fun navigateToDetails(corona : CoronaUseData){
        viewModel.focusCountry.value = corona
        viewModel.updateMapCountry()
        findNavController().navigate(R.id.action_all_to_details)
    }
}
