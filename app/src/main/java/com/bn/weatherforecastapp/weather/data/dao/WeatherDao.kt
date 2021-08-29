package com.bn.weatherforecastapp.weather.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity


@Dao
interface  WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateWeatherEntity(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getWeatherEntity(): LiveData<WeatherEntity>


    @Query("SELECT * FROM weather_table WHERE `key` LIKE :cityName")
    fun getWeatherEntityByCityName(cityName: String?): LiveData<WeatherEntity>
}