package com.eneye.soilapp.presentation.screens_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eneye.soilapp.core.navigation.ScreenRoutes


@Composable
fun BottomNavigationBarPhillip(
    item: List<NavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavigationItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(


    ){
        item.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        if(item.badgeCount > 0){
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                },
                            ) {

                            }
                        }else{
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.title,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(24.dp)

                            )
                        }

                    }
                },
                label = {
                    if(selected){
                        Text(
                            item.title,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }else{
                        Text(item.title)

                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),
            )
        }

    }

}

//@Composable
//fun BottomNavigationBar(
//
//    navController: NavController
//){
//    val navigationItems = listOf(
//        NavigationItem(
//            title = "SoilDashBoard",
//            icon = Icons.Default.Home,
//            route = ScreenRoutes.DashBoardFragment.route
//        ),
//        NavigationItem(
//            title = "SoilHealth",
//            icon = Icons.Default.Favorite,
//            route = ScreenRoutes.SoilHealthFragment.route
//        ),
////        NavigationItem(
////            title = "Charts",
////            icon = Icons.Default.Settings,
////            route = ScreenRoutes.ChartsFragment.route
////        )
//    )
//    val selectedNavigationIndex = rememberSaveable {
//        mutableStateOf(0)
//    }
//    NavigationBar(
//        containerColor = Color.White
//    ){
//        navigationItems.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = selectedNavigationIndex.value == index,
//                onClick = {
//                    selectedNavigationIndex.value = index
//                    navController.navigate(item.route)
//                },
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = item.title
//                    )
//                },
//                label = {
//                    Text(
//                        item.title,
//                        color = if(index == selectedNavigationIndex.value) Color.Black else Color.Gray
//                    )
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.surface,
//                    indicatorColor = MaterialTheme.colorScheme.primary
//                ),
//            )
//        }
//
//    }
//}