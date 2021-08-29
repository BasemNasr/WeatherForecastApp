package com.bn.weatherforecastapp.weather.data.models

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)