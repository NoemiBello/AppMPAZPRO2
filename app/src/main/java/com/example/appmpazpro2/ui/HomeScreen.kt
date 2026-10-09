package com.example.appmpazpro2.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.appmpazpro2.ui.utils.obtenerWindowSizeClass
import com.example.appmpazpro2.ui.screens.HomeScreenCompact
import com.example.appmpazpro2.ui.screens.HomeScreenExpanded
import com.example.appmpazpro2.ui.screens.HomeScreenMedium
@Composable
fun HomeScreen() {

    val windowSizeClass = obtenerWindowSizeClass()

    when (windowSizeClass.widthSizeClass) {

        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact()
        }

        WindowWidthSizeClass.Medium -> {
            HomeScreenMedium()
        }

        WindowWidthSizeClass.Expanded -> {
            HomeScreenExpanded()
        }
    }
}