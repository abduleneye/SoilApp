package com.eneye.soilapp.data.remote.repo

import com.eneye.soilapp.domain.model.AiResponseModel
import com.eneye.soilapp.domain.model.SensorDataPostBody
import com.eneye.soilapp.domain.model.SensorParameterModel
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SensorApi {
    @GET("/channels/2589522/feeds.json?results=2")
    suspend fun  getSensorParameters(): retrofit2.Response<SensorParameterModel>

    @POST("https://ai-based-soil-monitoring.onrender.com/predict")
    suspend fun postSensorData(@Body sensorData: SensorDataPostBody): retrofit2.Response<AiResponseModel>
    companion object{
        val BASE_URL = "https://api.thingspeak.com"
    }
}