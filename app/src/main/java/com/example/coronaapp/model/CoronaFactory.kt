package com.example.coronaapp.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.vm.CoronaViewModel

class CoronaFactory (val context : Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val apiService = ApiService.getCoronaApi()
        val repository = CoronaRespository(apiService,context)
        return CoronaViewModel(repository) as T
    }

}