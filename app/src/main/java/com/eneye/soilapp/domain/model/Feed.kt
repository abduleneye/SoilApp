package com.eneye.soilapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class Feed(
    @SerializedName("created_at")
    val date: String,

    @SerializedName("entry_id")
    val id: Int,

    @SerializedName("field1")
    val nitrogen: String,

    @SerializedName("field2")
    val phosphorus: String,

    @SerializedName("field3")
    val potassium: String,

    @SerializedName("field4")
    val soilPh: String,

    @SerializedName("field5")
    val conductivity: String,

    @SerializedName("field6")
    val temperature: String,

    @SerializedName("field7")
    val soilMoisture: String
): Parcelable