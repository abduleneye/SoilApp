package com.eneye.soilapp.core.di

import android.app.Application
import com.eneye.soilapp.data.remote.repo.SensorApi
import com.eneye.soilapp.data.remote.repo.SensorParameterApiRepoImplementation
import com.eneye.soilapp.domain.repo.SensorParameterApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun providesSensorRepo(): SensorApi{
        return Retrofit
            .Builder()
            .baseUrl(SensorApi.BASE_URL)
            .client(SensorApi.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SensorApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSensorRepoApiRepo(sensorApi: SensorApi, app: Application): SensorParameterApiRepo{
        return SensorParameterApiRepoImplementation(sensorApi = sensorApi)
    }
}