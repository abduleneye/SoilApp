package com.eneye.soilapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun onEvent(event: UiEventClass){
        when(event){
            is UiEventClass.getSensorData -> {
                getSensorParameters()
            }
        }
    }
}
