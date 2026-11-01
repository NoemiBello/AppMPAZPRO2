package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido

@Composable
fun GestionContenidoScreen(
    contenidos: List<ContenidoUiState>,
    onAprobarClick: (Int) -> Unit,
    onRechazarClick: (Int) -> Unit,
    onOcultarClick: (Int) -> Unit,
    onVerAsignaturasClick: () -> Unit,
    onRevisarRespuestasClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Gestion de contenidos",
            style = MaterialTheme.typography.headlineMedium
        )

        // Ir a las 4 asignaturas
        Button(
            onClick = onVerAsignaturasClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver asignaturas")
        }

        // Revisar respuestas enviadas
        Button(
            onClick = onRevisarRespuestasClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Revisar respuestas")
        }

        Text(
            text = "Contenidos pendientes",
            style = MaterialTheme.typography.titleMedium
        )

        if (contenidos.isEmpty()) {

            Text(
                text = "No hay contenidos pendientes",
                modifier = Modifier.padding(top = 12.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = contenidos,
                    key = { contenido ->
                        contenido.id
                    }
                ) { contenido ->

                    Card(
                        modifier =
                            Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(16.dp),
                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = contenido.titulo,
                                style =
                                    MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text =
                                    "Asignatura: ${contenido.asignatura}"
                            )

                            Text(
                                text =
                                    "Unidad: ${contenido.unidad}"
                            )

                            Text(
                                text =
                                    "Objetivo: ${contenido.objetivo}"
                            )

                            Text(
                                text =
                                    "Tipo: ${contenido.tipo}"
                            )

                            Text(
                                text = contenido.contenido
                            )

                            Text(
                                text =
                                    "Estado: ${contenido.estado.name}"
                            )

                            if (
                                contenido.estado ==
                                EstadoContenido.PENDIENTE
                            ) {

                                Column(
                                    modifier =
                                        Modifier.fillMaxWidth(),
                                    verticalArrangement =
                                        Arrangement.spacedBy(8.dp)
                                ) {

                                    Button(
                                        onClick = {
                                            onAprobarClick(
                                                contenido.id
                                            )
                                        },
                                        modifier =
                                            Modifier.fillMaxWidth()
                                    ) {
                                        Text("Aprobar")
                                    }

                                    Button(
                                        onClick = {
                                            onRechazarClick(
                                                contenido.id
                                            )
                                        },
                                        modifier =
                                            Modifier.fillMaxWidth()
                                    ) {
                                        Text("Rechazar")
                                    }

                                    Button(
                                        onClick = {
                                            onOcultarClick(
                                                contenido.id
                                            )
                                        },
                                        modifier =
                                            Modifier.fillMaxWidth()
                                    ) {
                                        Text("Ocultar")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}