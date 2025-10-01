package com.eneye.soilapp.presentation.screens

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eneye.soilapp.domain.model.SensorDataPostBody
import com.eneye.soilapp.presentation.AppUiState
import com.eneye.soilapp.presentation.UiEventClass
import com.eneye.soilapp.presentation.screens_components.CustomCircularProgressBar

@Composable
fun SoilHealthFragment(
    appUiState: AppUiState,
    uiEvent: (UiEventClass) -> Unit
){
    if (appUiState.loadingPredictionResult) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
    }else if(!appUiState.loadingPredictionResult && appUiState.predictionErrorOccurred == false){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ){
            item {
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                IconButton(
                    onClick = {
                        uiEvent(
                            UiEventClass.postToGetPredictionResult(sensorData = SensorDataPostBody(
                                humidity = 60,
                                moisture = 40,
                                nitrogen = 50,
                                phosphorous = 30,
                                potassium = 20,
                                soilType = appUiState.soilType,
                                temperature = 28
                            ))
                        )

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null
                    )
                }

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(0.9f)
                    // .background(color = Color.Black)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CustomCircularProgressBar(
                            percentage = (appUiState.predictionResult.soilQuality.toFloat()/100),
                            number = 3,
                        )
                    }

                }

                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                )

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(0.9f)
                    // .background(color = Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            "Recommended Fertilizer",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )
                        Text(
                            appUiState.predictionResult.recommendedFertilizer
                        )
                    }

                }

                Spacer(
                    modifier = Modifier
                        .height(100.dp)
                )
            }

        }

    }
}            else if(appUiState.errorOccurred){

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
                    uiEvent(
                        UiEventClass.postToGetPredictionResult(sensorData = SensorDataPostBody(
                            humidity = 60,
                            moisture = 40,
                            nitrogen = 50,
                            phosphorous = 30,
                            potassium = 20,
                            soilType = "Loamy",
                            temperature = 28
                        ))
                    )
                }
            ) {
                Text("Retry")
            }

        }


    }

}