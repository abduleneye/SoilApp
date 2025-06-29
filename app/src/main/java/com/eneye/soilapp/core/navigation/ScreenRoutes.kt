package com.eneye.soilapp.core.navigation

sealed class ScreenRoutes(
    val  route: String

){
    object MainScreen: ScreenRoutes(route = "main_screen")
    object DashBoardFragment: ScreenRoutes(route = "dashboard_screen")
    object SoilHealthFragment: ScreenRoutes(route = "health_screen")
    object ChartsFragment: ScreenRoutes(route = "charts_screen")


}