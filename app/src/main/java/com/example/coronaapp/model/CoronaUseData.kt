package com.example.coronaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_corona")
data class CoronaUseData(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
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
    val flag : String
)