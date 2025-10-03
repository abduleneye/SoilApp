package com.eneye.soilapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class AiResponseModel(
    @SerializedName("recommended_fertilizer")
    val recommendedFertilizer: String,

    @SerializedName("soil_quality")
    val soilQuality: Double
): Parcelable