package com.bn.weatherforecastapp.weather.data.models.response


import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.Coord
import com.bn.weatherforecastapp.weather.data.models.WeatherData
import com.google.gson.annotations.SerializedName

 class WeatherResponse(

     @SerializedName("city")
    val city: City,
     @SerializedName("list")
    val list: List<WeatherData>,
     @SerializedName("cnt")
     val cnt: Int,
     @SerializedName("message")
     val message: Int,
     @SerializedName("cod")
    val cod: String
)