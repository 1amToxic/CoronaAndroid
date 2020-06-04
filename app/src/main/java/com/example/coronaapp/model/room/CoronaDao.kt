package com.example.coronaapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coronaapp.model.CoronaUseData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface CoronaDao{
    @Insert
    fun insertAll(corona : CoronaUseData)
    @Query("select * from tbl_corona")
    fun getAllData() : List<CoronaUseData>
}