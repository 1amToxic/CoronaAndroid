package com.example.coronaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.coronaapp.R
import com.example.coronaapp.databinding.FragmentDetailsBinding
import com.example.coronaapp.model.CoronaFactory
import com.example.coronaapp.vm.CoronaViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    lateinit var imageFlag : ImageView
    private val viewModel : CoronaViewModel by activityViewModels(factoryProducer = {
        CoronaFactory(requireContext())
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        viewModel.focusCountry.observe(viewLifecycleOwner, Observer {
            binding.country = it
            Glide.with(this)
                .load(it.flag)
                .centerCrop()
                .into(imageFlag)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageFlag = view.findViewById(R.id.flag)
    }
}
