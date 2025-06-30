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
import com.eneye.soilapp.presentation.AppViewModel
import com.eneye.soilapp.presentation.screens.ChartFragment
import com.eneye.soilapp.presentation.screens.DashBoardFragment
import com.eneye.soilapp.presentation.screens.MainScreen
import com.eneye.soilapp.presentation.screens.SoilHealthFragment

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
){
    val appUiViewModel = hiltViewModel<AppViewModel>()
    val appUiState = appUiViewModel.appScreenUiState.collectAsState()
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
               appUiState = appUiState.value,
                uiEvent = appUiViewModel::onEvent
            )
        }
        composable(route = ScreenRoutes.SoilHealthFragment.route){
            SoilHealthFragment()
        }
        composable(route = ScreenRoutes.ChartsFragment.route){
            ChartFragment()
        }


    }

}