package com.example.coronaapp.model

import com.google.gson.annotations.SerializedName

data class CoronaAllData(
    @SerializedName("updated") val updated : Long,
    @SerializedName("country") val country : String,
    @SerializedName("countryInfo") val countryInfo : CountryInfo,
    @SerializedName("cases") val cases : Int,
    @SerializedName("todayCases") val todayCases : Int,
    @SerializedName("deaths") val deaths : Int,
    @SerializedName("todayDeaths") val todayDeaths : Int,
    @SerializedName("recovered") val recovered : Int,
    @SerializedName("todayRecovered") val todayRecovered : Int,
    @SerializedName("active") val active : Int,
    @SerializedName("critical") val critical : Int,
    @SerializedName("casesPerOneMillion") val casesPerOneMillion : Double,
    @SerializedName("deathsPerOneMillion") val deathsPerOneMillion : Double,
    @SerializedName("tests") val tests : Int,
    @SerializedName("testsPerOneMillion") val testsPerOneMillion : Int,
    @SerializedName("population") val population : Int,
    @SerializedName("continent") val continent : String,
    @SerializedName("oneCasePerPeople") val oneCasePerPeople : Int,
    @SerializedName("oneDeathPerPeople") val oneDeathPerPeople : Int,
    @SerializedName("oneTestPerPeople") val oneTestPerPeople : Int,
    @SerializedName("activePerOneMillion") val activePerOneMillion : Double,
    @SerializedName("recoveredPerOneMillion") val recoveredPerOneMillion : Double,
    @SerializedName("criticalPerOneMillion") val criticalPerOneMillion : Double
)
data class CountryInfo(
    @SerializedName("_id") val _id : Int,
    @SerializedName("iso2") val iso2 : String,
    @SerializedName("iso3") val iso3 : String,
    @SerializedName("lat") val lat : Float?,
    @SerializedName("long") val long : Float?,
    @SerializedName("flag") val flag : String
)