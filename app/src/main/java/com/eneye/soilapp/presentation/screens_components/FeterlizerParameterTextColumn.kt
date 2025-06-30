package com.eneye.soilapp.presentation.screens_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FerterlizerParametertextColumn(
    parameterName: String,
    parameterValue: String,
    paraMeterUnit: String,
){
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            parameterName,
            fontWeight = FontWeight.Bold
        )
        Text(
            parameterValue,
            fontWeight = FontWeight.W400

        )
        Text(
            paraMeterUnit,
            fontWeight = FontWeight.W500

        )
    }
}