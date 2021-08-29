package com.bn.weatherforecastapp.weather.data.background

import android.os.AsyncTask
import com.bn.weatherforecastapp.weather.data.dao.WeatherDao
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity


class UpdateWeatherEntityAsyncTask(weatherDao: WeatherDao, weatherEntity: WeatherEntity) :
    AsyncTask<Void?, Void?, Void?>() {
    private val weatherDao: WeatherDao
    private val weatherEntity: WeatherEntity

    init {
        this.weatherDao = weatherDao
        this.weatherEntity = weatherEntity
    }

    override fun doInBackground(vararg params: Void?): Void? {
        weatherDao.updateWeatherEntity(weatherEntity);
        return null;
    }
}
