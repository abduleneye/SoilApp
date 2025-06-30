package com.eneye.soilapp.data.remote.repo

import com.eneye.soilapp.domain.model.SensorParameterModel
import okhttp3.Response
import retrofit2.http.GET

interface SensorApi {
    @GET("/channels/2589522/feeds.json?results=2")
    suspend fun  getSensorParameters(): retrofit2.Response<SensorParameterModel>

    companion object{
        val BASE_URL = "https://api.thingspeak.com"
    }
}