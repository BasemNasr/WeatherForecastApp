package com.bn.weatherforecastapp.weather.data.converters

import androidx.room.TypeConverter
import com.bn.weatherforecastapp.weather.data.models.WeatherData
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class WeatherConventer {

    @TypeConverter
    fun fromList(listOfItem: List<WeatherData>): String? {
        if (listOfItem == null) {
            return null
        }
        val type: Type = object : TypeToken<List<WeatherData>>() {}.type
        return Gson().toJson(listOfItem, type)
    }

    @TypeConverter
    fun toList(json: String?): List<WeatherData?>? {
        if (json == null) {
            return null
        }
        val type: Type = object : TypeToken<List<WeatherData?>?>() {}.type
        return Gson().fromJson<List<WeatherData?>>(json, type)
    }
}