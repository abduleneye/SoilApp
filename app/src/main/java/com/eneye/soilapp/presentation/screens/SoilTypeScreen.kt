package com.eneye.soilapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.eneye.soilapp.core.navigation.ScreenRoutes
import com.eneye.soilapp.presentation.AppUiState
import com.eneye.soilapp.presentation.UiEventClass
import com.eneye.soilapp.presentation.screens_components.SoilTypeDropDownMenuBox
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SoilTypeScreen(
    appUiState: AppUiState,
    uiEvent: (UiEventClass) -> Unit,
    navController: NavHostController,
    ){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,

        )
        {
            Text(
                "Welcome !!!"
            )
            Column {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                )

                {
                    Text(
                        text = "Soil Type",
                        modifier = Modifier
                        // .height(22.dp)
                        //.width(62.dp)
//                            .padding(
//                                vertical = 89.dp,
//                                horizontal = 57.dp
//                            )
                        ,
                        //color = YourTripHeaderTextColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.5).sp
                    )
                }
                SoilTypeDropDownMenuBox(
                    uiState = appUiState,
                    uiEvent = uiEvent
                )
            }

            //Spacer(modifier = Modifier.height(64.dp))
            Button(
                onClick = {
                    if (appUiState.soilType.isEmpty()){
                        Toast.makeText(
                            context,
                            "Please select a soil type",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        CoroutineScope(Dispatchers.Main).launch {
                            navController.navigate(ScreenRoutes.MainScreen.route)
                            uiEvent(UiEventClass.getSensorData)


                        }


                    }

                },
            ) {
                Text(
                    "Next"
                )
            }
        }
    }
}