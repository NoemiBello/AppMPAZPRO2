package com.example.appmpazpro2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmpazpro2.ui.components.BottomNavigationBar
import com.example.appmpazpro2.ui.screens.AsignaturasScreen
import com.example.appmpazpro2.ui.screens.InicioScreen
import com.example.appmpazpro2.ui.screens.ProgresoScreen
import com.example.appmpazpro2.ui.screens.RegistroScreen
import com.example.appmpazpro2.ui.screens.ResumenScreen
import com.example.appmpazpro2.viewmodel.UsuarioViewModel
import com.example.appmpazpro2.ui.screens.PerfilScreen
import com.example.appmpazpro2.viewmodel.PerfilViewModel
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()
    val perfilViewModel: PerfilViewModel = viewModel()

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
                InicioScreen(
                    navController = navController
                )
            }

            composable(Screen.Asignaturas.route) {
                AsignaturasScreen()
            }

            composable(Screen.Progreso.route) {
                ProgresoScreen()
            }

            composable("perfil") {
                PerfilScreen(
                    viewModel = perfilViewModel
                )
            }

            composable("registro") {

                val estado by usuarioViewModel.uiState.collectAsState()

                RegistroScreen(
                    estado = estado,
                    errores = estado.errores,
                    onNombreChange = usuarioViewModel::actualizarNombre,
                    onCorreoChange = usuarioViewModel::actualizarCorreo,
                    onClaveChange = usuarioViewModel::actualizarClave,
                    onDireccionChange = usuarioViewModel::actualizarDireccion,
                    onAceptaTerminosChange = usuarioViewModel::actualizarTerminos,
                    onRegistrarClick = {
                        if (usuarioViewModel.validarFormulario()) {
                            navController.navigate("resumen")
                        }
                    }
                )
            }

            composable("resumen") {
                ResumenScreen(
                    viewModel = usuarioViewModel
                )
            }
        }
    }
}