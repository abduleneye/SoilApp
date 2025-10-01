package com.eneye.soilapp.domain.model

import com.google.gson.annotations.SerializedName

data class AiResponseModel(
    @SerializedName("recommended_fertilizer")
    val recommendedFertilizer: String,

    @SerializedName("soil_quality")
    val soilQuality: Double
)