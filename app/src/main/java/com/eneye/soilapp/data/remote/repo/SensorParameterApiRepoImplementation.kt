package com.eneye.soilapp.data.remote.repo

import com.eneye.soilapp.domain.model.Feed
import com.eneye.soilapp.domain.model.SensorParameterModel
import com.eneye.soilapp.domain.repo.SensorParameterApiRepo
import com.voyatek.tripapp.features.trips.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import android.util.Log
import com.eneye.soilapp.domain.model.AiResponseModel
import com.eneye.soilapp.domain.model.SensorDataPostBody

class SensorParameterApiRepoImplementation(
    private val sensorApi: SensorApi
): SensorParameterApiRepo {
    override fun getSensorParameters(): Flow<Resource<SensorParameterModel>> = flow {
        emit(
            Resource.Loading(

            )
        )
        try {
            val result = sensorApi.getSensorParameters().body()

            emit(
                Resource.Success(
                    data = result
                    )
            )
        }catch (e: HttpException){
            emit(
                Resource.Error(
                    message = e.message,
                    data = null
                )
            )
            Log.d("Error Message","An Error Occured from catch 1 ${e.toString()}")

        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = e.message,
                    data = null
                )
            )
            Log.d("Error Message","An Error Occured from catch 2 ${e.toString()}")

        }

    }

    override fun postSensorData(data: SensorDataPostBody): Flow<Resource<AiResponseModel>> = flow{
        emit(Resource.Loading())

        try {
            val result = sensorApi.postSensorData(sensorData = data)
            emit(Resource.Success(
                data = result.body()
            ))
            Log.d(
                "Prediction Result",
                result.body().toString()
            )


        } catch (e: Exception){
            Log.d("Prediction error", e.message.toString())
            emit(
                Resource.Error(
                    message = e.message,
                    data = null
                )
            )
        }
    }
}