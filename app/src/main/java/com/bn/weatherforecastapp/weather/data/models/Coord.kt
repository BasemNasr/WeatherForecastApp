package com.bn.weatherforecastapp.weather.data.models

import com.google.gson.annotations.SerializedName

class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)