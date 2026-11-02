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
import com.example.appmpazpro2.viewmodel.RolUsuario

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    rolSeleccionado: RolUsuario?
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
                navController.navigate(
                    Screen.Inicio.route
                )
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
                navController.navigate(
                    Screen.Asignaturas.route
                )
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

        if (
            rolSeleccionado == RolUsuario.DOCENTE ||
            rolSeleccionado == RolUsuario.ESTUDIANTE
        ) {

            NavigationBarItem(
                selected = rutaActual == Screen.Progreso.route,
                onClick = {
                    navController.navigate(
                        Screen.Progreso.route
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.ShowChart,
                        contentDescription =
                            if (
                                rolSeleccionado ==
                                RolUsuario.DOCENTE
                            ) {
                                "Revisar respuestas"
                            } else {
                                "Progreso"
                            }
                    )
                },
                label = {
                    Text(
                        if (
                            rolSeleccionado ==
                            RolUsuario.DOCENTE
                        ) {
                            "Revisión"
                        } else {
                            "Progreso"
                        }
                    )
                }
            )
        }
    }
}