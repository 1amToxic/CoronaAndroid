package com.example.coronaapp.model

data class CoronaUseDataK(
    val id : Int = 0,
    val updated : Long,
    val country : String,
    val cases : String,
    val todayCases : String,
    val deaths : String,
    val todayDeaths : String,
    val recovered : String,
    val todayRecovered : String,
    val active : String,
    val critical : String,
    val flag : String,
    val lat : Double?,
    val long : Double?
)