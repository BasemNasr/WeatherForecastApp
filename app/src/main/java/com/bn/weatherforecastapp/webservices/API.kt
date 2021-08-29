package com.bn.kotlinproject.webservices

import com.bn.weatherforecastapp.weather.data.models.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.*


interface  API {

    @GET("data/2.5/forecast?")
    fun getCurrentWeatherData(@Query("q") CityName: String
                              , @Query("APPID") app_id: String): Call<WeatherResponse>

}

