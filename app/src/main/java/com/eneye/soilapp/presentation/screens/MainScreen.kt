package com.eneye.soilapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eneye.soilapp.R
import com.eneye.soilapp.core.AnimatedSplash
import com.eneye.soilapp.core.navigation.ScreenRoutes
import com.eneye.soilapp.presentation.AppUiState
import com.eneye.soilapp.presentation.AppViewModel
import com.eneye.soilapp.presentation.UiEventClass
import com.eneye.soilapp.presentation.screens_components.BottomNavigationBarPhillip
import com.eneye.soilapp.presentation.screens_components.NavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    appUiState: AppUiState,
    uiEvent: (UiEventClass) -> Unit

){
    val navController = rememberNavController()


    val navigationItems = listOf(
        NavigationItem(
            title = "SoilDashboard",
            icon = R.drawable.tempt_two,
            route = ScreenRoutes.DashBoardFragment.route
        ),
        NavigationItem(
            title = "SoilHealth",
            icon = R.drawable.health_one,
            route = ScreenRoutes.SoilHealthFragment.route
        ),
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBarPhillip(
                item = navigationItems,
                navController = navController,
                modifier = Modifier,
                onItemClick = {
                    navController.popBackStack()
                    navController.navigate(it.route)
                }
            )
        }
    ) { innerPadding ->
//        val appUiViewModel = hiltViewModel<AppViewModel>()
//        val appUiState = appUiViewModel.appScreenUiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.DashBoardFragment.route
        ){

//        composable(route = ScreenRoutes.DashBoardFragment.route){
//            MainScreen(
//                navController = navController
//            )
//
//        }
            composable(route = ScreenRoutes.DashBoardFragment.route){
                DashBoardFragment(
                    appUiState = appUiState,
                    uiEvent = uiEvent
                )
            }
            composable(route = ScreenRoutes.SoilHealthFragment.route){
                SoilHealthFragment(
                    appUiState = appUiState,
                    uiEvent = uiEvent
                )
            }
            composable(route = ScreenRoutes.ChartsFragment.route){
                ChartFragment()
            }


        }
    }
}