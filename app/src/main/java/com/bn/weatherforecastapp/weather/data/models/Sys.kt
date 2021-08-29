package com.bn.weatherforecastapp.weather.data.models

import com.google.gson.annotations.SerializedName

class Sys(
    @SerializedName("pod")
    val pod: String
)