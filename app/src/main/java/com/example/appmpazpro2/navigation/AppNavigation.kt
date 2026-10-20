package com.example.appmpazpro2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmpazpro2.ui.HomeScreen
import com.example.appmpazpro2.ui.components.BottomNavigationBar
import com.example.appmpazpro2.ui.screens.AsignaturasScreen
import com.example.appmpazpro2.ui.screens.InicioScreen
import com.example.appmpazpro2.ui.screens.ProgresoScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Inicio.route) {
                InicioScreen()
            }

            composable(Screen.Asignaturas.route) {
                AsignaturasScreen()
            }

            composable(Screen.Progreso.route) {
                ProgresoScreen()
            }
        }
    }
}