package com.example.coronaapp.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coronaapp.R
import com.example.coronaapp.model.CoronaUseData

class CoronaAdapter(val context : Context,val onClickItem : (CoronaUseData) -> Unit) : ListAdapter<CoronaUseData,CoronaAdapter.CoronaViewHolder>(CoronaDiff){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoronaAdapter.CoronaViewHolder {
        return CoronaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_corona,parent,false))
    }
    object CoronaDiff : DiffUtil.ItemCallback<CoronaUseData>(){
        override fun areItemsTheSame(oldItem: CoronaUseData, newItem: CoronaUseData): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: CoronaUseData, newItem: CoronaUseData): Boolean {
            return oldItem == newItem
        }

    }
    inner class CoronaViewHolder(val itemview : View) : RecyclerView.ViewHolder(itemview){
        private val thumbnail : ImageView
        private val countryName : TextView
        private val totalCases : TextView
        private val totalDeaths : TextView
        init {
            thumbnail = itemview.findViewById(R.id.thumbnail)
            countryName = itemview.findViewById(R.id.country_name)
            totalCases = itemview.findViewById(R.id.total_cases)
            totalDeaths = itemview.findViewById(R.id.total_deaths)
        }
        fun bind(corona : CoronaUseData){
            Glide.with(context)
                .load(corona.flag)
                .centerCrop()
                .into(thumbnail)
            countryName.text = corona.country
            totalCases.text = "Total cases" + corona.cases
            totalDeaths.text = "Total deaths " +   corona.deaths
            itemview.setOnClickListener{
                Log.d("AppLog","Click")
                onClickItem(corona)
            }
        }
    }
    override fun onBindViewHolder(holder: CoronaAdapter.CoronaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}