package com.eneye.soilapp.presentation

import com.eneye.soilapp.domain.model.Feed

data class AppUiState(
    val sensorParameters: List<Feed> = emptyList<Feed>(),
    val loadingParameters: Boolean = false,
    val errorOccurred: Boolean = false,
    val errorMessage: String = ""
)