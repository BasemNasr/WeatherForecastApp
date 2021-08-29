package com.bn.weatherforecastapp.weather.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bn.weatherforecastapp.utils.Constants
import com.bn.weatherforecastapp.weather.data.converters.CityConventer
import com.bn.weatherforecastapp.weather.data.converters.WeatherConventer
import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.WeatherData


@Entity(tableName = Constants.KEY_WEATHER_TABLE_NAME)
data class WeatherEntity(val CityName:String,val weatherss: List<WeatherData>,val cityInfoo:City) {


    @PrimaryKey()
    @ColumnInfo(name = "key")
    var key:String = CityName
    @TypeConverters(WeatherConventer::class)
    @ColumnInfo(name = "weatherdata")
    var weathers: List<WeatherData> = weatherss

    @TypeConverters(CityConventer::class)
    @ColumnInfo(name = "cityinfo")
    var cityInfo: City = cityInfoo






}