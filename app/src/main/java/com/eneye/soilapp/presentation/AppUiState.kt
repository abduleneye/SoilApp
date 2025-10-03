package com.eneye.soilapp.presentation

import android.os.Parcelable
import com.eneye.soilapp.domain.model.AiResponseModel
import com.eneye.soilapp.domain.model.Feed

@kotlinx.parcelize.Parcelize
data class AppUiState(
    val sensorParameters: List<Feed> = emptyList<Feed>(),
    val loadingParameters: Boolean = false,
    val errorOccurred: Boolean = false,
    val errorMessage: String = "",
    val soilType: String = "",
    val loadingPredictionResult: Boolean = false,
    val predictionResult: AiResponseModel = AiResponseModel(
        recommendedFertilizer = "not available",
        soilQuality = 0.0
    ),
    val predictionResultErrorMessage: String = "",
    val predictionErrorOccurred: Boolean = false,

    ): Parcelable