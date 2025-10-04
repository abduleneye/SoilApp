package com.eneye.soilapp.data.remote.repo

import com.eneye.soilapp.domain.model.AiResponseModel
import com.eneye.soilapp.domain.model.SensorDataPostBody
import com.eneye.soilapp.domain.model.SensorParameterModel
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface SensorApi {
    //@GET("/channels/2589522/feeds.json?results=2")
    @GET("/channels/3094251/feeds.json?api_key=ERJW316E52L3WW3Y&results=2")
    suspend fun  getSensorParameters(): retrofit2.Response<SensorParameterModel>

    @POST("https://ai-based-soil-monitoring.onrender.com/predict")
    suspend fun postSensorData(@Body sensorData: SensorDataPostBody): retrofit2.Response<AiResponseModel>
    companion object{
        val BASE_URL = "https://api.thingspeak.com"
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(
                60,
                TimeUnit.SECONDS
            )// Time to establish connection
            .readTimeout(120,
                TimeUnit.SECONDS) // Time waiting for server to respond
            .writeTimeout(60,
                TimeUnit.SECONDS
            )// time to send request body
            .build()
    }
}