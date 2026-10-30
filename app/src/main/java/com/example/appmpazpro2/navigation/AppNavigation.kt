package com.example.appmpazpro2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.ui.components.BottomNavigationBar
import com.example.appmpazpro2.ui.screens.AsignaturasScreen
import com.example.appmpazpro2.ui.screens.ContenidoEstudianteScreen
import com.example.appmpazpro2.ui.screens.FormularioContenidoScreen
import com.example.appmpazpro2.ui.screens.InicioScreen
import com.example.appmpazpro2.ui.screens.PerfilScreen
import com.example.appmpazpro2.ui.screens.ProgresoScreen
import com.example.appmpazpro2.ui.screens.RegistroScreen
import com.example.appmpazpro2.ui.screens.ResumenScreen
import com.example.appmpazpro2.ui.screens.SeleccionPerfilScreen
import com.example.appmpazpro2.ui.screens.UnidadObjetivoScreen
import com.example.appmpazpro2.viewmodel.ContenidoViewModel
import com.example.appmpazpro2.viewmodel.PerfilViewModel
import com.example.appmpazpro2.viewmodel.ProgresoViewModel
import com.example.appmpazpro2.viewmodel.RolUsuario
import com.example.appmpazpro2.viewmodel.RolViewModel
import com.example.appmpazpro2.viewmodel.UsuarioViewModel

private const val RUTA_SELECCION_PERFIL = "seleccion_perfil"
private const val RUTA_FORMULARIO_CONTENIDO = "formulario_contenido"
private const val RUTA_CONTENIDO_ESTUDIANTE = "contenido_estudiante"
private const val RUTA_PERFIL = "perfil"
private const val RUTA_REGISTRO = "registro"
private const val RUTA_RESUMEN = "resumen"

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()
    val perfilViewModel: PerfilViewModel = viewModel()
    val rolViewModel: RolViewModel = viewModel()
    val contenidoViewModel: ContenidoViewModel = viewModel()
    val progresoViewModel: ProgresoViewModel = viewModel()

    val rolSeleccionado by rolViewModel.rolSeleccionado.collectAsState()

    var asignaturaSeleccionada by remember {
        mutableStateOf("Lenguaje")
    }

    val rutaActual =
        navController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    Scaffold(
        bottomBar = {

            if (rutaActual != RUTA_SELECCION_PERFIL) {

                BottomNavigationBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = RUTA_SELECCION_PERFIL,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(RUTA_SELECCION_PERFIL) {

                SeleccionPerfilScreen(
                    onUtpClick = {

                        rolViewModel.seleccionarRol(
                            RolUsuario.UTP
                        )

                        navController.navigate(
                            Screen.Asignaturas.route
                        ) {

                            popUpTo(
                                RUTA_SELECCION_PERFIL
                            ) {
                                inclusive = true
                            }
                        }
                    },

                    onDocenteClick = {

                        rolViewModel.seleccionarRol(
                            RolUsuario.DOCENTE
                        )

                        navController.navigate(
                            Screen.Asignaturas.route
                        ) {

                            popUpTo(
                                RUTA_SELECCION_PERFIL
                            ) {
                                inclusive = true
                            }
                        }
                    },

                    onEstudianteClick = {

                        rolViewModel.seleccionarRol(
                            RolUsuario.ESTUDIANTE
                        )

                        navController.navigate(
                            Screen.Asignaturas.route
                        ) {

                            popUpTo(
                                RUTA_SELECCION_PERFIL
                            ) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.Inicio.route) {

                InicioScreen(
                    navController = navController
                )
            }

            composable(Screen.Asignaturas.route) {

                AsignaturasScreen(

                    onLenguajeClick = {

                        asignaturaSeleccionada =
                            "Lenguaje"

                        navController.navigate(
                            Screen.UnidadObjetivo.route
                        )
                    },

                    onMatematicaClick = {

                        asignaturaSeleccionada =
                            "Matematica"

                        navController.navigate(
                            Screen.UnidadObjetivo.route
                        )
                    },

                    onCienciasClick = {

                        asignaturaSeleccionada =
                            "Ciencias Naturales"

                        navController.navigate(
                            Screen.UnidadObjetivo.route
                        )
                    },

                    onHistoriaClick = {

                        asignaturaSeleccionada =
                            "Historia"

                        navController.navigate(
                            Screen.UnidadObjetivo.route
                        )
                    }
                )
            }

            composable(Screen.UnidadObjetivo.route) {

                UnidadObjetivoScreen(
                    asignatura =
                        asignaturaSeleccionada,

                    onObjetivoClick = {

                        if (
                            rolViewModel
                                .puedeGestionarContenido()
                        ) {

                            contenidoViewModel
                                .actualizarAsignatura(
                                    asignaturaSeleccionada
                                )

                            navController.navigate(
                                RUTA_FORMULARIO_CONTENIDO
                            )

                        } else if (
                            rolViewModel
                                .puedeResponderContenido()
                        ) {

                            navController.navigate(
                                RUTA_CONTENIDO_ESTUDIANTE
                            )
                        }
                    }
                )
            }

            composable(
                RUTA_FORMULARIO_CONTENIDO
            ) {

                val uiState by
                contenidoViewModel
                    .uiState
                    .collectAsState()

                val errores by
                contenidoViewModel
                    .errores
                    .collectAsState()

                FormularioContenidoScreen(
                    uiState = uiState,
                    errores = errores,

                    onAsignaturaChange =
                        contenidoViewModel::
                        actualizarAsignatura,

                    onUnidadChange =
                        contenidoViewModel::
                        actualizarUnidad,

                    onObjetivoChange =
                        contenidoViewModel::
                        actualizarObjetivo,

                    onTipoChange =
                        contenidoViewModel::
                        actualizarTipo,

                    onTituloChange =
                        contenidoViewModel::
                        actualizarTitulo,

                    onContenidoChange =
                        contenidoViewModel::
                        actualizarContenido,

                    onGuardarClick = {

                        if (
                            contenidoViewModel
                                .guardarContenido(
                                    rolSeleccionado
                                )
                        ) {

                            navController.navigate(
                                RUTA_CONTENIDO_ESTUDIANTE
                            )
                        }
                    }
                )
            }

            composable(
                RUTA_CONTENIDO_ESTUDIANTE
            ) {

                val contenidos by
                contenidoViewModel
                    .contenidos
                    .collectAsState()

                val respuesta by
                progresoViewModel
                    .respuestaActual
                    .collectAsState()

                val contenidoVisible =
                    contenidoViewModel
                        .contenidosVisibles(
                            rolSeleccionado,
                            asignaturaSeleccionada
                        )
                        .firstOrNull()
                        ?: contenidos.firstOrNull()
                        ?: ContenidoUiState(
                            titulo =
                                "Contenido pendiente",
                            contenido =
                                "Aun no hay contenido guardado para esta asignatura."
                        )

                ContenidoEstudianteScreen(
                    titulo =
                        contenidoVisible.titulo,

                    contenido =
                        contenidoVisible.contenido,

                    respuesta =
                        respuesta,

                    onRespuestaChange =
                        progresoViewModel::
                        actualizarRespuesta,

                    onEnviarClick = {

                        if (
                            progresoViewModel
                                .registrarRespuesta()
                        ) {

                            navController.navigate(
                                Screen.Progreso.route
                            )
                        }
                    }
                )
            }

            composable(
                Screen.Progreso.route
            ) {

                val progreso by
                progresoViewModel
                    .progreso
                    .collectAsState()

                val porcentaje by
                progresoViewModel
                    .porcentaje
                    .collectAsState()

                val respuestas by
                progresoViewModel
                    .respuestasRegistradas
                    .collectAsState()

                ProgresoScreen(
                    progreso = progreso,
                    porcentaje = porcentaje,
                    respuestasRegistradas =
                        respuestas
                )
            }

            composable(RUTA_PERFIL) {

                PerfilScreen(
                    viewModel = perfilViewModel
                )
            }

            composable(RUTA_REGISTRO) {

                val estado by
                usuarioViewModel
                    .uiState
                    .collectAsState()

                RegistroScreen(
                    estado = estado,
                    errores = estado.errores,

                    onNombreChange =
                        usuarioViewModel::
                        actualizarNombre,

                    onCorreoChange =
                        usuarioViewModel::
                        actualizarCorreo,

                    onClaveChange =
                        usuarioViewModel::
                        actualizarClave,

                    onDireccionChange =
                        usuarioViewModel::
                        actualizarDireccion,

                    onAceptaTerminosChange =
                        usuarioViewModel::
                        actualizarTerminos,

                    onRegistrarClick = {

                        if (
                            usuarioViewModel
                                .validarFormulario()
                        ) {

                            navController.navigate(
                                RUTA_RESUMEN
                            )
                        }
                    }
                )
            }

            composable(RUTA_RESUMEN) {

                ResumenScreen(
                    viewModel =
                        usuarioViewModel
                )
            }
        }
    }
}