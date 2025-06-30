package com.eneye.soilapp.presentation

import com.eneye.soilapp.domain.model.SensorParameterModel

data class AppUiState(
    val sensorParameters: List<SensorParameterModel> = emptyList(),
    val loadingParameters: Boolean = false,
    val errorOccurred: Boolean = false,
    val errorMessage: String = ""
)