package com.eneye.soilapp.presentation.screens_components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.eneye.soilapp.presentation.AppUiState
import com.eneye.soilapp.presentation.UiEventClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoilTypeDropDownMenuBox(
    uiState: AppUiState,
    uiEvent: (UiEventClass) -> Unit
){
    var expanded by remember{

        mutableStateOf(false)
    }

    var list = listOf(
        "Sandy",
        "Loamy",
        "Black",
        "Red",
        "Clayey"
    )
    var selectedItem by remember {
        mutableStateOf("")
    }

    var textFilledSize  by remember {
        mutableStateOf(Size.Zero)
    }
    var icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    } else{
        Icons.Filled.KeyboardArrowDown

    }


    Column(
        verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        OutlinedTextField(
            enabled = false,
            //value = selectedItem,
            value = uiState.soilType,

            onValueChange = {
                // selectedItem = it
                //uiEvent(UiEventClass.setTripTravelStyle(it))

            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { co_ordinates ->
                    textFilledSize = co_ordinates.size.toSize()
                }
//                    modifier = Modifier
//                    .fillMaxWidth()
//                .height(188.dp)
            ,
            label = {
                Text(text = "Select your soil type")
            },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable {
                    expanded = !expanded
                })
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color.DarkGray,
                unfocusedBorderColor = Color.DarkGray,
                // disabledContainerColor = Color.DarkGray,
                disabledBorderColor = Color.DarkGray,
                disabledTextColor = Color.DarkGray,
                disabledLabelColor = Color.DarkGray,
                disabledTrailingIconColor = Color.DarkGray
            )
        )



        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFilledSize.width.toDp()})
        ) {

            list.forEach {label ->

                DropdownMenuItem(
                    onClick = {
                        // selectedItem = label
                        uiEvent(UiEventClass.setSoilType(label))
                        expanded = false
                    },
                    text = {
                        Text(text = label)
                    },
                    modifier = Modifier,
                    leadingIcon = {

                    },
                    trailingIcon = {
                        Icons.Default.Check

                    },
                    enabled = true,
                    colors = MenuItemColors(
                        textColor = Color.Black,
                        leadingIconColor =Color.LightGray,
                        trailingIconColor = Color.LightGray,
                        disabledTextColor = Color.LightGray,
                        disabledLeadingIconColor = Color.LightGray,
                        disabledTrailingIconColor = Color.LightGray
                    ),
                    contentPadding = PaddingValues(all = 0.dp)
                )

            }

        }
//        Spacer(
//            modifier = Modifier.height(300.dp)
//        )


    }

}

@Preview(
    showSystemUi = true

)
@Composable
fun DropDownPreview(){

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        // TripTravelStyleDropDownMenuBox()

    }
}