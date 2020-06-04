package com.example.coronaapp.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

const val mainUrl = "https://corona.lmao.ninja/v2/"
interface Api{
    @GET("countries")
    fun getAllCoronaData() : Call<List<CoronaAllData>>
}

object ApiService{
    var instance : Retrofit? = null
    fun getRetrofit() : Retrofit{
        if(instance == null){
            val client = OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .callTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build()
            instance = Retrofit.Builder()
                .baseUrl(mainUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return instance!!
    }
    fun getCoronaApi() : Api{
        return getRetrofit().create(Api::class.java)
    }
}