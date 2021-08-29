package com.bn.kotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bn.weatherforecastapp.R
import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.WeatherData

internal class WeathesAdapter(private var context:Context, private var list:List<WeatherData>,
    private var cityInfo:City)
    : RecyclerView.Adapter<WeathesAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list.get(position)
        holder.timeTV.text = item.dtTxt
        holder.tempTextView.text = item.main.temp.toString()
        holder.pressureTextView.text = item.main.pressure.toString()
        holder.humidityTV.text = item.main.humidity.toString()
        holder.weatherTV.text = item.weather.get(0).main.toString()
        holder.windSpeedTV.text = item.wind.speed.toString()
        holder.container.setOnClickListener{
            Toast.makeText(context,"${cityInfo.name}",Toast.LENGTH_SHORT).show()
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var timeTV: TextView = view.findViewById(R.id.timeTV)
        var tempTextView: TextView = view.findViewById(R.id.tempTV)
        var pressureTextView: TextView = view.findViewById(R.id.pressureTV)
        var humidityTV: TextView = view.findViewById(R.id.humidityTV)
        var weatherTV: TextView = view.findViewById(R.id.weatherTV)
        var windSpeedTV: TextView = view.findViewById(R.id.windSpeedTV)
        var container: LinearLayout = view.findViewById(R.id.container)
    }


}