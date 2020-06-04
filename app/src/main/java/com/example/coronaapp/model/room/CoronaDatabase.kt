package com.example.coronaapp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coronaapp.model.CoronaUseData

@Database(entities = [CoronaUseData::class],version = 1)
abstract class CoronaDatabase :RoomDatabase(){
    abstract fun coronaDao() : CoronaDao
    companion object{
        private var instance : CoronaDatabase? = null
        fun getDatabase(context : Context) : CoronaDatabase{
            synchronized(CoronaDatabase::class.java){
                instance = Room.databaseBuilder(
                    context,
                    CoronaDatabase::class.java,
                    "corona.db"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}