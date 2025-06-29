package com.eneye.soilapp.presentation.screens_components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String,
    val badgeCount: Int = 0
)
