package com.acment.countryapp.recyclerviewAdaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acment.countryapp.R
import com.acment.countryapp.model.Countries
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class CountryListAdaptor(val context: Context,val countryList:ArrayList<Countries>):
            RecyclerView.Adapter<CountryListAdaptor.viewHolder>(){

    inner class  viewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

   val countryName = itemView.findViewById<TextView>(R.id.countryName)
   val capital = itemView.findViewById<TextView>(R.id.capitalName)
   val flag = itemView.findViewById<ImageView>(R.id.flagcountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.country_list_item,parent,false)
        return viewHolder(layout)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val itemData = countryList[position]
       holder.countryName.text = itemData.name
        holder.capital.text = itemData.capital
        Glide.with(context)
            .load(itemData.flag)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.flag)




    }

    override fun getItemCount(): Int {
      return countryList.size
    }



    fun updateCountries(newCountries:List<Countries>){
        countryList.clear()
        countryList.addAll(newCountries)
        notifyDataSetChanged()
    }

}