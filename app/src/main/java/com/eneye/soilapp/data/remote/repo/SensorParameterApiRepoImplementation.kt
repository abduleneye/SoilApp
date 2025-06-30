package com.eneye.soilapp.data.remote.repo

import com.eneye.soilapp.domain.model.SensorParameterModel
import com.eneye.soilapp.domain.repo.SensorParameterApiRepo
import com.voyatek.tripapp.features.trips.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class SensorParameterApiRepoImplementation(
    private val sensorApi: SensorApi
): SensorParameterApiRepo {
    override fun getSensorParameters(): Flow<Resource<List<SensorParameterModel>>> = flow {
        emit(
            Resource.Loading(

            )
        )
        try {
            val result = sensorApi.getSensorParameters()
            emit(
                Resource.Success(
                    data = result.body()
                    )
            )
        }catch (e: HttpException){
            emit(
                Resource.Error(
                    message = e.message,
                    data = null
                )
            )
        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = e.message,
                    data = null
                )
            )
        }

    }
}