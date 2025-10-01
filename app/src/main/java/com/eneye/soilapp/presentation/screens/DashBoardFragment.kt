package com.eneye.soilapp.presentation.screens

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eneye.soilapp.R
import com.eneye.soilapp.core.navigation.ScreenRoutes
import com.eneye.soilapp.presentation.AppUiState
import com.eneye.soilapp.presentation.UiEventClass
import com.eneye.soilapp.presentation.screens_components.DefaultCardSample
import com.eneye.soilapp.presentation.screens_components.FerterlizerParametertextColumn

@Composable
fun DashBoardFragment(
    appUiState: AppUiState,
    uiEvent: (UiEventClass) -> Unit
){
//    LaunchedEffect(Unit) {
//        uiEvent(UiEventClass.getSensorData)
//
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (appUiState.loadingParameters) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CircularProgressIndicator()
            }
        }else if(appUiState.sensorParameters.isNotEmpty()){
            LazyColumn(
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 16.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {
                item {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    IconButton(
                        onClick = {
                            uiEvent(UiEventClass.getSensorData)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DefaultCardSample(
                            modifier = Modifier
                                .height(100.dp)
                                .weight(1f),

                            item = R.drawable.temp_one,
                            sensorParameter = "Temperature",
                            sensorValue = "${appUiState.sensorParameters.last().temperature }\u00B0C"
                        )

                        Spacer(
                            modifier = Modifier
                                .width(12.dp)
                        )

                        DefaultCardSample(
                            modifier = Modifier
                                .height(100.dp)
                                .weight(1f),
                            item = R.drawable.moisture_one,
                            sensorParameter = "Moisture",
                            sensorValue = "${appUiState.sensorParameters.last().soilMoisture }%"
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        DefaultCardSample(
                            modifier = Modifier
                                .height(100.dp)
                                .weight(1f),
                            item = R.drawable.conductivity_one,
                            sensorParameter = "Conductivity",
                            sensorValue = "${appUiState.sensorParameters.last().conductivity }\u00B5s/cm"
                        )
                        Spacer(
                            modifier = Modifier
                                .width(12.dp)
                        )

                        DefaultCardSample(
                            modifier = Modifier
                                .height(100.dp)
                                .weight(1f),
                            item = R.drawable.ph_one,
                            sensorParameter = "PH Level",
                            sensorValue = appUiState.sensorParameters.last().soilPh
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )

                    Card(
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(180.dp)


                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ){
                            FerterlizerParametertextColumn(
                                parameterName = "N",
                                parameterValue = appUiState.sensorParameters.last().nitrogen,
                                paraMeterUnit = "mg/Kg"
                            )
                            FerterlizerParametertextColumn(
                                parameterName = "P",
                                parameterValue = appUiState.sensorParameters.last().phosphorus,
                                paraMeterUnit = "mg/Kg"
                            )
                            FerterlizerParametertextColumn(
                                parameterName = "K",
                                parameterValue = appUiState.sensorParameters.last().potassium,
                                paraMeterUnit = "mg/Kg"
                            )
                        }

                    }

                }




            }
        }
            else if(appUiState.errorOccurred){

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Text(
                     text =  appUiState.errorMessage,
                      textAlign = TextAlign.Center
                  )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Button(
                        onClick = {
                            uiEvent(UiEventClass.getSensorData)
                        }
                    ) {
                        Text("Retry")
                    }

                }


            }

        }
    }
