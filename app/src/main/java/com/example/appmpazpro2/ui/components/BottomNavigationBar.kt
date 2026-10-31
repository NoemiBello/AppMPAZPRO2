package com.example.appmpazpro2.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appmpazpro2.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    val backStackEntry =
        navController.currentBackStackEntryAsState().value

    val rutaActual =
        backStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        NavigationBarItem(
            selected = rutaActual == Screen.Inicio.route,
            onClick = {
                navController.navigate(Screen.Inicio.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            label = {
                Text("Inicio")
            }
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Asignaturas.route,
            onClick = {
                navController.navigate(Screen.Asignaturas.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = "Asignaturas"
                )
            },
            label = {
                Text("Asignaturas")
            }
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Progreso.route,
            onClick = {
                navController.navigate(Screen.Progreso.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.ShowChart,
                    contentDescription = "Progreso"
                )
            },
            label = {
                Text("Progreso")
            }
        )
    }
}