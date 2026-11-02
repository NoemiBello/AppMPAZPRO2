package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.appmpazpro2.ui.components.BarraVolver

@Composable
fun GestionContenidoScreen(
    contenidos: List<ContenidoUiState>,
    onAprobarClick: (Int) -> Unit,
    onRechazarClick: (Int) -> Unit,
    onOcultarClick: (Int) -> Unit,
    onVolverClick: () -> Unit,
    onVerAsignaturasClick: () -> Unit,
    onEliminarClick: (Int) -> Unit,
    onReactivarClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BarraVolver(
            titulo = "Gestion de contenidos",
            onVolverClick = onVolverClick
        )



        // Ir a las 4 asignaturas
        Button(
            onClick = onVerAsignaturasClick, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver asignaturas")
        }

        Text(
            text = "Contenidos pendientes", style = MaterialTheme.typography.titleMedium
        )

        if (contenidos.isEmpty()) {

            Text(
                text = "No hay contenidos pendientes", modifier = Modifier.padding(top = 12.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = contenidos, key = { contenido ->
                        contenido.id
                    }) { contenido ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = contenido.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Asignatura: ${contenido.asignatura}"
                            )

                            Text(
                                text = "Unidad: ${contenido.unidad}"
                            )

                            Text(
                                text = "Objetivo: ${contenido.objetivo}"
                            )

                            Text(
                                text = "Tipo: ${contenido.tipo}"
                            )

                            Text(
                                text = contenido.contenido
                            )

                            val textoEstado = when (contenido.estado) {
                                EstadoContenido.PENDIENTE -> "Pendiente"
                                EstadoContenido.APROBADO -> "Aprobado"
                                EstadoContenido.RECHAZADO -> "Rechazado"
                                EstadoContenido.OCULTO -> "Deshabilitada"
                            }

                            Text(
                                text = "Estado: $textoEstado"
                            )

                            when (contenido.estado) {

                                EstadoContenido.PENDIENTE -> {

                                    Button(
                                        onClick = {
                                            onAprobarClick(contenido.id)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Aprobar")
                                    }

                                    Button(
                                        onClick = {
                                            onRechazarClick(contenido.id)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Rechazar")
                                    }
                                }

                                EstadoContenido.APROBADO -> {

                                    Button(
                                        onClick = {
                                            onOcultarClick(contenido.id)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Deshabilitar")
                                    }
                                }

                                EstadoContenido.OCULTO -> {

                                    Button(
                                        onClick = {
                                            onReactivarClick(contenido.id)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Reactivar")
                                    }
                                }

                                EstadoContenido.RECHAZADO -> {

                                    Text(
                                        text = "Contenido rechazado",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }

                                // UTP siempre puede eliminar
                            Button(
                                onClick = {
                                    onEliminarClick(contenido.id)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}