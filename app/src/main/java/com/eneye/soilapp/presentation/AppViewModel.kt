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

    init {
        getSensorParameters()
    }
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

                            postToGetPredictionResult()
                        }
                    }

                    is Resource.Loading -> {
                        _appScreenUiState.update {
                            it.copy(
                                loadingParameters = true
                            )
                        }

                    }
                }
            }.launchIn(this)
        }
    }

    private fun postToGetPredictionResult(){
        viewModelScope.launch {
            sensorApiRepo.postSensorData(data = SensorDataPostBody(
                humidity = 60,
                moisture = 40,
                nitrogen = 50,
                phosphorous = 30,
                potassium = 20,
                soilType = "Loamy",
                temperature = 28
            )).onEach { result ->
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

            }
        }
    }
}
