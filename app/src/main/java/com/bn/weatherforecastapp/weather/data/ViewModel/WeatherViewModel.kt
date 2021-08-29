package com.bn.weatherforecastapp.weather.data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.bn.kotlinproject.webservices.API
import com.bn.weatherforecastapp.database.WeatherRoomDatabase
import com.bn.weatherforecastapp.weather.data.dao.WeatherDao
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity
import com.bn.weatherforecastapp.weather.data.repo.WeatherRepository

class WeatherViewModel (application: Application) : AndroidViewModel(application) {
    private val weatherRepository: WeatherRepository
    private val weatherDao: WeatherDao



    init {
        weatherDao = WeatherRoomDatabase.getDatabase(application).weatherDao()
        weatherRepository = WeatherRepository(weatherDao,application)
    }

    fun getWeatherEnitityByCityName(cityName: String): LiveData<WeatherEntity> {
        return weatherRepository.getWeatherEntityByCityName(cityName)
    }

}