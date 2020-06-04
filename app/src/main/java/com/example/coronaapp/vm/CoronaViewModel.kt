package com.example.coronaapp.vm

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronaapp.model.Api
import com.example.coronaapp.model.CoronaAllData
import com.example.coronaapp.model.CoronaRespository
import com.example.coronaapp.model.CoronaUseData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class CoronaViewModel (private val repository : CoronaRespository) : ViewModel(){
    val isFirstFetch = MutableLiveData<Boolean>(
        true
    )
    val listCorona =  MutableLiveData<List<CoronaUseData>>()
    val focusCountry = MutableLiveData<CoronaUseData>()
    val isLoading = MutableLiveData<Boolean>(
        false
    )
    val isError = MutableLiveData<Boolean>(
        false
    )
    val isNavigate = MutableLiveData<Boolean>(
        false
    )
    init {
        isLoading.value = true
        repository.getAllCoronaData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                listCorona.value = it.map {
                    CoronaUseData(
                        updated = it.updated,
                        country =  it.country,
                        cases = it.cases.toString(),
                        todayCases =  it.todayCases.toString(),
                        deaths = it.deaths.toString(),
                        todayDeaths = it.todayDeaths.toString(),
                        recovered =  it.recovered.toString(),
                        todayRecovered = it.todayRecovered.toString(),
                        flag = it.countryInfo.flag,
                        active = it.active.toString(),
                        critical = it.critical.toString()
                    )
                }
                focusCountry.value = listCorona.value!!.filter { it.country == "Vietnam" }[0]
                if(isFirstFetch.value!!) {
                    saveDataLocal()
                    isFirstFetch.value = false
                }
            },{
                isLoading.value = false
                isError.value = true
                getDataFromLocal()
                Log.d("AppLog",it.toString())
//                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show
            },{
                //complete
                isLoading.value = false
            })

    }
    private fun saveDataLocal(){
        repository.saveAllDataLocal(listCorona.value!!)
    }
    private fun getDataFromLocal(){
        repository.getAllDataFromLocal().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listCorona.value = it
            },{

            },{

            })
    }
}