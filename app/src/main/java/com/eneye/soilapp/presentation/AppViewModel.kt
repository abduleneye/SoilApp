package com.eneye.soilapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneye.soilapp.domain.model.SensorDataPostBody
import com.eneye.soilapp.domain.repo.SensorParameterApiRepo
import com.voyatek.tripapp.features.trips.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private  val sensorApiRepo: SensorParameterApiRepo
): ViewModel(){
    private  var _appScreenUiState = MutableStateFlow(AppUiState())
    var appScreenUiState = _appScreenUiState.asStateFlow()

//    init {
//        getSensorParameters()
//    }
    private  fun getSensorParameters(){
        viewModelScope.launch {
            sensorApiRepo.getSensorParameters().onEach { result ->
                when(result){
                    is Resource.Error ->{
                        _appScreenUiState.update {
                            it.copy(
                                errorOccurred = true,
                                loadingParameters = false,
                                errorMessage = result.message.toString()
                            )
                        }
                    }
                    is Resource.Success -> {
                        if(result.data != null){
                            _appScreenUiState.update {
                                it.copy(
                                    loadingParameters = false,
                                    errorOccurred = false,
                                    sensorParameters = result.data.feeds
                                )
                            }

                            postToGetPredictionResult(
//                                dataPostBody = SensorDataPostBody(
//                                    humidity = "60.3".toDouble().toInt(),
//                                    moisture = 40,
//                                    nitrogen = 50,
//                                    phosphorous = 30,
//                                    potassium = 20,
//                                    soilType = _appScreenUiState.value.soilType,
//                                    temperature = 28
//                                )
                                dataPostBody = SensorDataPostBody(
                                    humidity = result.data.feeds.last().soilMoisture.toDouble().toInt(),
                                    moisture = result.data.feeds.last().soilMoisture.toDouble().toInt(),
                                    nitrogen = result.data.feeds.last().nitrogen.toDouble().toInt(),
                                    phosphorous = result.data.feeds.last().phosphorus.toDouble().toInt(),
                                    potassium = result.data.feeds.last().potassium.toDouble().toInt(),
                                    soilType = _appScreenUiState.value.soilType,
                                    temperature = result.data.feeds.last().temperature.toDouble().toInt()
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _appScreenUiState.update {
                            it.copy(
                                loadingParameters = true,
                                sensorParameters = emptyList()
                            )
                        }

                    }
                }
            }.launchIn(this)
        }
    }

    private fun postToGetPredictionResult(dataPostBody: SensorDataPostBody){
        viewModelScope.launch {
            sensorApiRepo.postSensorData(
                data = SensorDataPostBody(
                humidity = dataPostBody.humidity,
                moisture = dataPostBody.moisture,
                nitrogen = dataPostBody.nitrogen,
                phosphorous = dataPostBody.phosphorous,
                potassium = dataPostBody.potassium,
                soilType = dataPostBody.soilType,
                temperature = dataPostBody.temperature
            )
            ).onEach { result ->
                when(result){
                    is Resource.Error ->{
                        _appScreenUiState.update {
                            it.copy(
                                predictionErrorOccurred = true,
                                loadingPredictionResult = false,
                                predictionResultErrorMessage = result.message.toString()
                            )
                        }
                    }
                    is Resource.Success -> {
                        if(result.data != null){
                            _appScreenUiState.update {
                                it.copy(
                                    loadingPredictionResult = false,
                                    predictionErrorOccurred = false,
                                    predictionResult = result.data
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        _appScreenUiState.update {
                            it.copy(
                                loadingPredictionResult = true
                            )
                        }

                    }
                }
            }.launchIn(this)
        }
    }

    fun onEvent(event: UiEventClass){
        when(event){
            is UiEventClass.getSensorData -> {
                getSensorParameters()
            }

            is UiEventClass.setSoilType -> {
                _appScreenUiState.update {
                    it.copy(
                        soilType = event.soilType
                    )
                }
            }

            is UiEventClass.postToGetPredictionResult -> {
                postToGetPredictionResult(
                    dataPostBody = SensorDataPostBody(
                        humidity = _appScreenUiState.value.sensorParameters.last().soilMoisture.toDouble().toInt(),
                        moisture = _appScreenUiState.value.sensorParameters.last().soilMoisture.toDouble().toInt(),
                        nitrogen = _appScreenUiState.value.sensorParameters.last().nitrogen.toDouble().toInt(),
                        phosphorous = _appScreenUiState.value.sensorParameters.last().phosphorus.toDouble().toInt(),
                        potassium = _appScreenUiState.value.sensorParameters.last().potassium.toDouble().toInt(),
                        soilType = _appScreenUiState.value.soilType,
                        temperature = _appScreenUiState.value.sensorParameters.last().temperature.toDouble().toInt()
                    )
                )
            }
        }
    }
}
