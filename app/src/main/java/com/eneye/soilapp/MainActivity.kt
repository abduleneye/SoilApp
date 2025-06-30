package com.eneye.soilapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eneye.soilapp.core.navigation.NavGraph
import com.eneye.soilapp.core.navigation.ScreenRoutes
import com.eneye.soilapp.presentation.screens_components.BottomNavigationBarPhillip
import com.eneye.soilapp.presentation.screens_components.NavigationItem
import com.eneye.soilapp.ui.theme.SoilAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
//        NavigationItem(
//            title = "Charts",
//            icon = Icons.Default.Settings,
//            route = ScreenRoutes.ChartsFragment.route
//        )
            )


            SoilAppTheme {
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
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoilAppTheme {
        Greeting("Android")
    }
}