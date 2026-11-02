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
import com.example.appmpazpro2.model.EstadoContenido
import com.example.appmpazpro2.model.EstadoRespuesta

import com.example.appmpazpro2.ui.components.BottomNavigationBar

import com.example.appmpazpro2.ui.screens.AsignaturasScreen
import com.example.appmpazpro2.ui.screens.ContenidoEstudianteScreen
import com.example.appmpazpro2.ui.screens.FormularioContenidoScreen
import com.example.appmpazpro2.ui.screens.GestionContenidoScreen
import com.example.appmpazpro2.ui.screens.InicioScreen
import com.example.appmpazpro2.ui.screens.ObjetivosScreen
import com.example.appmpazpro2.ui.screens.PerfilScreen
import com.example.appmpazpro2.ui.screens.ProgresoScreen
import com.example.appmpazpro2.ui.screens.RegistroScreen
import com.example.appmpazpro2.ui.screens.ResumenScreen
import com.example.appmpazpro2.ui.screens.RevisionRespuestasScreen
import com.example.appmpazpro2.ui.screens.SeleccionPerfilScreen
import com.example.appmpazpro2.ui.screens.UnidadObjetivoScreen

import com.example.appmpazpro2.viewmodel.ContenidoViewModel
import com.example.appmpazpro2.viewmodel.PerfilViewModel
import com.example.appmpazpro2.viewmodel.ProgresoViewModel
import com.example.appmpazpro2.viewmodel.RolUsuario
import com.example.appmpazpro2.viewmodel.RolViewModel
import com.example.appmpazpro2.viewmodel.UsuarioViewModel


private const val RUTA_SELECCION_PERFIL =
    "seleccion_perfil"

private const val RUTA_FORMULARIO_CONTENIDO =
    "formulario_contenido"

private const val RUTA_CONTENIDO_ESTUDIANTE =
    "contenido_estudiante"

private const val RUTA_PERFIL =
    "perfil"

private const val RUTA_REGISTRO =
    "registro"

private const val RUTA_RESUMEN =
    "resumen"

private const val RUTA_GESTION_CONTENIDO =
    "gestion_contenido"

private const val RUTA_REVISION_RESPUESTAS =
    "revision_respuestas"

private const val RUTA_OBJETIVOS =
    "objetivos"


@Composable
fun AppNavigation() {

    val navController =
        rememberNavController()

    val usuarioViewModel:
            UsuarioViewModel = viewModel()

    val perfilViewModel:
            PerfilViewModel = viewModel()

    val rolViewModel:
            RolViewModel = viewModel()

    val contenidoViewModel:
            ContenidoViewModel = viewModel()

    val progresoViewModel:
            ProgresoViewModel = viewModel()


    val rolSeleccionado by
    rolViewModel
        .rolSeleccionado
        .collectAsState()


    var asignaturaSeleccionada by remember {
        mutableStateOf("Lenguaje")
    }


    var unidadSeleccionada by remember {
        mutableStateOf("")
    }


    var objetivoSeleccionado by remember {
        mutableStateOf("")
    }


    val rutaActual =
        navController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route


    Scaffold(

        bottomBar = {

            if (
                rutaActual !=
                RUTA_SELECCION_PERFIL
            ) {

                BottomNavigationBar(
                    navController =
                        navController
                )
            }
        }

    ) { innerPadding ->


        NavHost(

            navController =
                navController,

            startDestination =
                RUTA_SELECCION_PERFIL,

            modifier =
                Modifier.padding(
                    innerPadding
                )

        ) {


            // SELECCION DE PERFIL

            composable(
                RUTA_SELECCION_PERFIL
            ) {

                SeleccionPerfilScreen(

                    onUtpClick = {

                        rolViewModel
                            .seleccionarRol(
                                RolUsuario.UTP
                            )

                        navController
                            .navigate(
                                RUTA_GESTION_CONTENIDO
                            )
                    },


                    onDocenteClick = {

                        rolViewModel
                            .seleccionarRol(
                                RolUsuario.DOCENTE
                            )

                        navController
                            .navigate(
                                Screen
                                    .Asignaturas
                                    .route
                            )
                    },


                    onEstudianteClick = {

                        rolViewModel
                            .seleccionarRol(
                                RolUsuario.ESTUDIANTE
                            )

                        navController
                            .navigate(
                                Screen
                                    .Asignaturas
                                    .route
                            )
                    }
                )
            }


            // INICIO

            composable(
                Screen.Inicio.route
            ) {

                InicioScreen(
                    navController =
                        navController
                )
            }


            // ASIGNATURAS

            composable(
                Screen.Asignaturas.route
            ) {

                AsignaturasScreen(

                    onLenguajeClick = {

                        if (
                            rolViewModel
                                .puedeAccederAsignatura(
                                    "Lenguaje"
                                )
                        ) {

                            asignaturaSeleccionada =
                                "Lenguaje"

                            unidadSeleccionada = ""

                            navController
                                .navigate(
                                    Screen
                                        .UnidadObjetivo
                                        .route
                                )
                        }
                    },


                    onMatematicaClick = {

                        if (
                            rolViewModel
                                .puedeAccederAsignatura(
                                    "Matematica"
                                )
                        ) {

                            asignaturaSeleccionada =
                                "Matematica"

                            unidadSeleccionada = ""

                            navController
                                .navigate(
                                    Screen
                                        .UnidadObjetivo
                                        .route
                                )
                        }
                    },


                    onCienciasClick = {

                        if (
                            rolViewModel
                                .puedeAccederAsignatura(
                                    "Ciencias Naturales"
                                )
                        ) {

                            asignaturaSeleccionada =
                                "Ciencias Naturales"

                            unidadSeleccionada = ""

                            navController
                                .navigate(
                                    Screen
                                        .UnidadObjetivo
                                        .route
                                )
                        }
                    },


                    onHistoriaClick = {

                        if (
                            rolViewModel
                                .puedeAccederAsignatura(
                                    "Historia"
                                )
                        ) {

                            asignaturaSeleccionada =
                                "Historia"

                            unidadSeleccionada = ""

                            navController
                                .navigate(
                                    Screen
                                        .UnidadObjetivo
                                        .route
                                )
                        }
                    }
                )
            }


            // UNIDAD

            composable(
                Screen.UnidadObjetivo.route
            ) {

                UnidadObjetivoScreen(

                    asignatura =
                        asignaturaSeleccionada,

                    onObjetivoClick = { unidad ->

                        unidadSeleccionada =
                            unidad

                        objetivoSeleccionado = ""

                        navController.navigate(
                            RUTA_OBJETIVOS
                        )
                    }
                )
            }


            // OBJETIVOS

            composable(
                RUTA_OBJETIVOS
            ) {

                ObjetivosScreen(

                    asignatura =
                        asignaturaSeleccionada,

                    unidad =
                        unidadSeleccionada,

                    onObjetivoClick = { objetivo ->

                        objetivoSeleccionado =
                            objetivo


                        if (
                            rolViewModel
                                .puedeGestionarContenido()
                        ) {

                            contenidoViewModel
                                .actualizarAsignatura(
                                    asignaturaSeleccionada
                                )

                            contenidoViewModel
                                .actualizarUnidad(
                                    unidadSeleccionada
                                )

                            contenidoViewModel
                                .actualizarObjetivo(
                                    objetivoSeleccionado
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


            // FORMULARIO CONTENIDO

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

                    uiState =
                        uiState,

                    errores =
                        errores,


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


                    onPreguntaChange =
                        contenidoViewModel::
                        actualizarPregunta,


                    onGuardarClick = {

                        if (
                            contenidoViewModel
                                .guardarContenido(
                                    rolSeleccionado
                                )
                        ) {

                            navController
                                .navigate(
                                    Screen
                                        .Asignaturas
                                        .route
                                )
                        }
                    }
                )
            }


            // CONTENIDO DEL ESTUDIANTE

            composable(
                RUTA_CONTENIDO_ESTUDIANTE
            ) {

                val respuesta by
                progresoViewModel
                    .respuestaActual
                    .collectAsState()


                val respuestas by
                progresoViewModel
                    .respuestas
                    .collectAsState()


                val contenidoVisible =
                    contenidoViewModel
                        .contenidosVisibles(
                            rolSeleccionado,
                            asignaturaSeleccionada,
                            unidadSeleccionada,
                            objetivoSeleccionado
                        )
                        .firstOrNull()
                        ?: ContenidoUiState(
                            titulo = "Sin contenido",
                            contenido =
                                "Aun no hay contenido aprobado para este objetivo."
                        )


                val respuestaGuardada =
                    respuestas.firstOrNull {

                        it.contenidoId ==
                                contenidoVisible.id
                    }
                ContenidoEstudianteScreen(

                    titulo =
                        contenidoVisible.titulo,

                    contenido =
                        contenidoVisible.contenido,

                    pregunta =
                        contenidoVisible.pregunta,

                    respuesta =
                        respuesta,

                    hayContenido =
                        contenidoVisible.id != 0,

                    respuestaEnviada =
                        respuestaGuardada?.respuesta,

                    estadoRespuesta =
                        respuestaGuardada?.estado,

                    onRespuestaChange =
                        progresoViewModel::
                        actualizarRespuesta,

                    onEnviarClick = {

                        if (
                            contenidoVisible.id != 0
                        ) {

                            progresoViewModel
                                .registrarRespuesta(
                                    contenidoVisible.id
                                )
                        }
                    }
                )


            }

            // PROGRESO

            composable(
                Screen.Progreso.route
            ) {

                when (rolSeleccionado) {


                    RolUsuario.ESTUDIANTE -> {

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
                            .respuestas
                            .collectAsState()


                        val correctas =
                            respuestas.count {

                                it.estado ==
                                        EstadoRespuesta.CORRECTA
                            }


                        val incorrectas =
                            respuestas.count {

                                it.estado ==
                                        EstadoRespuesta.INCORRECTA
                            }


                        val pendientes =
                            respuestas.count {

                                it.estado ==
                                        EstadoRespuesta.PENDIENTE_REVISION
                            }


                        ProgresoScreen(

                            progreso =
                                progreso,

                            porcentaje =
                                porcentaje,

                            respuestasRegistradas =
                                respuestas.size,

                            respuestasCorrectas =
                                correctas,

                            respuestasIncorrectas =
                                incorrectas,

                            respuestasPendientes =
                                pendientes
                        )
                    }


                    RolUsuario.DOCENTE -> {

                        val respuestas by
                        progresoViewModel
                            .respuestas
                            .collectAsState()


                        val contenidos by
                        contenidoViewModel
                            .contenidos
                            .collectAsState()


                        RevisionRespuestasScreen(

                            respuestas =
                                respuestas,

                            contenidos =
                                contenidos,


                            onCorrectaClick = { id ->

                                progresoViewModel
                                    .marcarCorrecta(
                                        id,
                                        rolSeleccionado
                                    )
                            },


                            onIncorrectaClick = { id ->

                                progresoViewModel
                                    .marcarIncorrecta(
                                        id,
                                        rolSeleccionado
                                    )
                            }
                        )
                    }


                    RolUsuario.UTP -> {

                        val respuestas by
                        progresoViewModel
                            .respuestas
                            .collectAsState()


                        val contenidos by
                        contenidoViewModel
                            .contenidos
                            .collectAsState()


                        RevisionRespuestasScreen(

                            respuestas =
                                respuestas,

                            contenidos =
                                contenidos,


                            onCorrectaClick = { id ->

                                progresoViewModel
                                    .marcarCorrecta(
                                        id,
                                        rolSeleccionado
                                    )
                            },


                            onIncorrectaClick = { id ->

                                progresoViewModel
                                    .marcarIncorrecta(
                                        id,
                                        rolSeleccionado
                                    )
                            }
                        )
                    }


                    null -> {

                        // No se muestra nada si no hay rol seleccionado
                    }
                }
            }


            // PERFIL

            composable(
                RUTA_PERFIL
            ) {

                PerfilScreen(
                    viewModel =
                        perfilViewModel
                )
            }


            // REGISTRO

            composable(
                RUTA_REGISTRO
            ) {

                val estado by
                usuarioViewModel
                    .uiState
                    .collectAsState()


                RegistroScreen(

                    estado =
                        estado,

                    errores =
                        estado.errores,


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

                            navController
                                .navigate(
                                    RUTA_RESUMEN
                                )
                        }
                    }
                )
            }


            // RESUMEN

            composable(
                RUTA_RESUMEN
            ) {

                ResumenScreen(
                    viewModel =
                        usuarioViewModel
                )
            }


            // GESTION DE CONTENIDO UTP

            composable(
                RUTA_GESTION_CONTENIDO
            ) {

                val contenidos by
                contenidoViewModel
                    .contenidos
                    .collectAsState()


                val pendientes =
                    contenidos.filter {

                        it.estado ==
                                EstadoContenido.PENDIENTE
                    }


                GestionContenidoScreen(

                    contenidos =
                        pendientes,


                    onAprobarClick = { id ->

                        contenidoViewModel
                            .aprobarContenido(
                                id,
                                rolSeleccionado
                            )
                    },


                    onRechazarClick = { id ->

                        contenidoViewModel
                            .rechazarContenido(
                                id,
                                rolSeleccionado
                            )
                    },


                    onOcultarClick = { id ->

                        contenidoViewModel
                            .ocultarContenido(
                                id,
                                rolSeleccionado
                            )
                    },


                    onVerAsignaturasClick = {

                        navController.navigate(
                            Screen.Asignaturas.route
                        )
                    },


                    onRevisarRespuestasClick = {

                        navController.navigate(
                            RUTA_REVISION_RESPUESTAS
                        )
                    }
                )
            }


            // REVISION DE RESPUESTAS

            composable(
                RUTA_REVISION_RESPUESTAS
            ) {

                val respuestas by
                progresoViewModel
                    .respuestas
                    .collectAsState()


                val contenidos by
                contenidoViewModel
                    .contenidos
                    .collectAsState()


                RevisionRespuestasScreen(

                    respuestas =
                        respuestas,

                    contenidos =
                        contenidos,


                    onCorrectaClick = { id ->

                        progresoViewModel
                            .marcarCorrecta(
                                id,
                                rolSeleccionado
                            )
                    },


                    onIncorrectaClick = { id ->

                        progresoViewModel
                            .marcarIncorrecta(
                                id,
                                rolSeleccionado
                            )
                    }
                )
            }
        }
    }
}