package com.eneye.soilapp.presentation

import com.eneye.soilapp.domain.model.AiResponseModel
import com.eneye.soilapp.domain.model.SensorDataPostBody

sealed class UiEventClass {
    object getSensorData: UiEventClass()
    data class setSoilType(var soilType: String): UiEventClass()
    data class postToGetPredictionResult(var sensorData: SensorDataPostBody): UiEventClass()
}