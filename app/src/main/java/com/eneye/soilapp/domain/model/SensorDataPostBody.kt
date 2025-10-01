package com.eneye.soilapp.domain.model

import com.google.gson.annotations.SerializedName

data class SensorDataPostBody(

    @SerializedName("Humidity")
    val humidity: Int,

    @SerializedName("Moisture")
    val moisture: Int,

    @SerializedName("Nitrogen")
    val nitrogen: Int,

    @SerializedName("Phosphorous")
    val phosphorous: Int,

    @SerializedName("Potassium")
    val potassium: Int,

    @SerializedName("Soil_Type")
    val soilType: String,

    @SerializedName("Temparature")
    val temperature: Int
)