package com.eneye.soilapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eneye.soilapp.core.AnimatedSplash
import com.eneye.soilapp.presentation.AppViewModel
import com.eneye.soilapp.presentation.screens.ChartFragment
import com.eneye.soilapp.presentation.screens.DashBoardFragment
import com.eneye.soilapp.presentation.screens.MainScreen
import com.eneye.soilapp.presentation.screens.SoilHealthFragment

@Composable
fun AppNavGraph(
    navController: NavHostController,
){
    val appUiViewModel = hiltViewModel<AppViewModel>()
    val appUiState = appUiViewModel.appScreenUiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.SplashScreen.route
    ){
        composable(route = ScreenRoutes.SplashScreen.route){
            AnimatedSplash(
                navController = navController
            )
        }
        composable(route = ScreenRoutes.MainScreen.route){
            MainScreen(
               // navController = navController
            )
        }


    }

}