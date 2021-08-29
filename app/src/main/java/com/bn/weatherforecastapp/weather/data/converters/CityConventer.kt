package com.bn.weatherforecastapp.weather.data.converters

import androidx.room.TypeConverter
import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.WeatherData
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class CityConventer {

    @TypeConverter
    fun fromObject(city: City): String? {
        if (city == null) {
            return null
        }
        val type: Type = object : TypeToken<City>() {}.type
        return Gson().toJson(city, type)
    }

    @TypeConverter
    fun toObject(json: String?): City? {
        if (json == null) {
            return null
        }
        val type: Type = object : TypeToken<City?>() {}.type
        return Gson().fromJson<City>(json, type)
    }
}