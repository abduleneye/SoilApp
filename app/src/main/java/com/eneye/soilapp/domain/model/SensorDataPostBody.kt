package com.eneye.soilapp.domain.model

data class SensorDataPostBody(
    val humidity: Int,
    val moisture: Int,
    val nitrogen: Int,
    val phosphorous: Int,
    val potassium: Int,
    val soilType: String,
    val temperature: Int
)