package com.example.appmpazpro2.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("home")
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
            selected = false,
            onClick = {
                navController.navigate("asignaturas")
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
            selected = false,
            onClick = {
                navController.navigate("progreso")
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