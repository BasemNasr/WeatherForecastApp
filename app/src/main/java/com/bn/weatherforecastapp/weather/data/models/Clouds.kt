package com.bn.weatherforecastapp.weather.data.models

import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all")
    val all: Int
)