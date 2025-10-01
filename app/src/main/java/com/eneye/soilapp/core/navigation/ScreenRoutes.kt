package com.eneye.soilapp.core.navigation

sealed class ScreenRoutes(
    val  route: String

){
    object SplashScreen: ScreenRoutes(route = "splash_screen")
    object MainScreen: ScreenRoutes(route = "main_screen")
    object DashBoardFragment: ScreenRoutes(route = "dashboard_screen")
    object SoilHealthFragment: ScreenRoutes(route = "health_screen")
    object SoilTypeScreen: ScreenRoutes(route = "soil_type_screen")
    object ChartsFragment: ScreenRoutes(route = "charts_screen")


}