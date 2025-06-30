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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eneye.soilapp.R
import com.eneye.soilapp.core.navigation.ScreenRoutes
import com.eneye.soilapp.presentation.screens_components.DefaultCardSample
import com.eneye.soilapp.presentation.screens_components.FerterlizerParametertextColumn

@Composable
fun DashBoardFragment(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                        .height(100.dp)
                )
                IconButton(
                    onClick = {

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
                            .width(130.dp),

                        item = R.drawable.temp_one,
                        sensorParameter = "Temperature",
                        sensorValue = "13*C"
                    )

                    DefaultCardSample(
                        modifier = Modifier
                            .height(100.dp)
                            .width(130.dp),
                        item = R.drawable.moisture_one,
                        sensorParameter = "Moisture",
                        sensorValue = "13*C"
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
                            .width(130.dp),
                        item = R.drawable.conductivity_one,
                        sensorParameter = "Conductivity",
                        sensorValue = "13*C"
                    )

                    DefaultCardSample(
                        modifier = Modifier
                            .height(100.dp)
                            .width(130.dp),
                        item = R.drawable.ph_one,
                        sensorParameter = "PH",
                        sensorValue = "13*C"
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
                            parameterValue = "20",
                            paraMeterUnit = "mm/Kg"
                        )
                        FerterlizerParametertextColumn(
                            parameterName = "P",
                            parameterValue = "30",
                            paraMeterUnit = "mm/Kg"
                        )
                        FerterlizerParametertextColumn(
                            parameterName = "K",
                            parameterValue = "40",
                            paraMeterUnit = "mm/Kg"
                        )
                    }

                }

            }

        }
    }
    }