package com.example.coronaapp.model

import android.content.Context
import com.example.coronaapp.model.room.CoronaDao
import com.example.coronaapp.model.room.CoronaDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class CoronaRespository(private val coronaApi : Api,val context : Context){
    private val coronaDao : CoronaDao
    init {
        val database = CoronaDatabase.getDatabase(context)
        coronaDao = database.coronaDao()
    }
    fun saveAllDataLocal(listCorona : List<CoronaUseData>){
        listCorona.forEach{
            coronaDao.insertAll(it)
        }
    }
    fun getAllDataFromLocal() : Flowable<List<CoronaUseData>>{
        return Flowable.create<List<CoronaUseData>>({ emitter ->
            try {
                val list = coronaDao.getAllData()
                emitter.onNext(list)
                emitter.onComplete()
            }catch (ex : Exception){
                emitter.onError(ex)
            }
        },BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
    }
    fun getAllCoronaData() : Flowable<List<CoronaAllData>>{
        return Flowable.create<List<CoronaAllData>>({ emitter ->
            try {
                val list = coronaApi.getAllCoronaData().execute().body()
                emitter.onNext(list)
                emitter.onComplete()
            }catch (e : Exception){
                emitter.onError(e)
            }
        },BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
    }
}